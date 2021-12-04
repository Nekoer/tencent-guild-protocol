import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm") version "1.6.0"
    application
    kotlin("plugin.serialization") version "1.6.0"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.6.0"
    id("org.jetbrains.dokka") version "1.5.30" apply false
    `maven-publish`
    signing
    // see https://github.com/gradle-nexus/publish-plugin
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

group = "com.hcyacg"
version = "0.1.8"



repositories {
    mavenLocal()
    mavenCentral()
}


dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.5.2-native-mt")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
    implementation("com.squareup.okhttp3:okhttp:4.9.2")

    implementation("com.alibaba:fastjson:1.2.78")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    implementation("org.jsoup:jsoup:1.14.3")
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("ch.qos.logback:logback-core:1.2.7")
    implementation("ch.qos.logback:logback-classic:1.2.7")
    implementation("com.google.code.gson:gson:2.8.9")

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.6.0")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}


application {
    mainClass.set("MainKt")
}

noArg {
    annotation("com.lindroid.projectname.annotation.NoArg")
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.register<Zip>("stuffZip") {
    archiveBaseName.set("stuff")
    from("src/stuff")
}



publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = rootProject.name
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set(rootProject.name)
                description.set("The Robot Kotlin SDK on Tencent Channel")
                url.set("https://github.com/Nekoer/tencent-guild-protocol")
//                properties.set(mapOf(
//                    "myProp" to "value",
//                    "prop.with.dots" to "anotherValue"
//                ))
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("Nekoer")
                        name.set("Nekoer")
                        email.set("hcyacg@vip.qq.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/Nekoer/tencent-guild-protocol.git")
                    developerConnection.set("scm:git:ssh://github.com/Nekoer/tencent-guild-protocol.git")
                    url.set("https://github.com/Nekoer/tencent-guild-protocol")
                }
            }
        }
    }
    repositories {
        maven {
            // change URLs to point to your repos, e.g. http://my.org/repo
            val releasesRepoUrl = uri(layout.buildDirectory.dir("repos/releases"))
            val snapshotsRepoUrl = uri(layout.buildDirectory.dir("repos/snapshots"))
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
        }
    }
}

signing {
//    val signingKeyId: String? by project
//    val signingKey: String? by project
//    val signingPassword: String? by project
//    useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
//    sign(tasks["stuffZip"])
    sign(publishing.publications["mavenJava"])
//    sign(publishing.publications)
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}






nexusPublishing {
    repositories {
        sonatype {  //only for users registered in Sonatype after 24 Feb 2021
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))

            username.set(properties["myNexusTokenUsername"].toString())
            password.set(properties["myNexusTokenPassword"].toString())
        }
    }
}
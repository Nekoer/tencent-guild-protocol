import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    application
    kotlin("plugin.serialization") version "1.6.0"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.6.0"
}

group = "com.hcyacg"
version = "1.0"

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.5.2-native-mt")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
    implementation("com.squareup.okhttp3:okhttp:4.9.2")
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("com.alibaba:fastjson:1.2.78")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    implementation("org.jsoup:jsoup:1.14.3")
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
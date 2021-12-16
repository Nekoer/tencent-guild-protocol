package com.hcyacg.protocol.internal.entity

import com.google.gson.annotations.SerializedName


data class Identify(
    @SerializedName("d")
    var d: D,
) : Operation(2) {
    constructor(token: String) : this(D(token = token))
}


data class D(
    @SerializedName("intents")
    var intents: Long = 1610612739L,
    @SerializedName("properties")
    var properties: Properties = Properties(),
    @SerializedName("shard")
    var shard: List<Int> = listOf(0, 1),
    @SerializedName("token")
    var token: String
)


data class Properties(
    @SerializedName("\$browser")
    var browser: String = "okhttp",
    @SerializedName("\$device")
    var device: String = "tencent-guild-protocol by Nekoer",
    @SerializedName("\$os")
    var os: String? = System.getProperty("os.name").split(" ").firstOrNull() ?: "unknown"
)
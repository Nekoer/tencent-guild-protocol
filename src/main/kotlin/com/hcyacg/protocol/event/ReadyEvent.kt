package com.hcyacg.protocol.event

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import com.hcyacg.protocol.event.api.User


@NoArg

data class ReadyEvent(
    @SerializedName("session_id")
    val sessionId: String,
    @SerializedName("shard")
    val shard: List<Int>,
    @SerializedName("user")
    val user: User,
    @SerializedName("version")
    val version: Int
)
package com.hcyacg.protocol.event
import com.hcyacg.protocol.anno.NoArg
import com.hcyacg.protocol.event.api.User
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@NoArg
@Serializable
data class ReadyEvent(
    @SerialName("session_id")
    val session_id: String,
    @SerialName("shard")
    val shard: List<Int>,
    @SerialName("user")
    val user: User,
    @SerialName("version")
    val version: Int
)
package com.hcyacg.protocol.entity

import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class Announces (
    @SerialName("guild_id")
    val guild_id:String,
    @SerialName("channel_id")
    val channel_id:String,
    @SerialName("message_id")
    val message_id:String,
)
package com.hcyacg.protocol.event

import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class AudioActionEvent (
    @SerialName("guild_id")
    val guild_id:String,
    @SerialName("channel_id")
    val channel_id:String,
    @SerialName("audio_url")
    val audio_url:String,
    @SerialName("text")
    val text:String
)
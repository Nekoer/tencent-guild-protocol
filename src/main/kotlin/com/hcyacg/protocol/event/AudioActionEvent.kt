package com.hcyacg.protocol.event

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class AudioActionEvent(
    @SerializedName("guild_id")
    val guildId: String,
    @SerializedName("channel_id")
    val channelId: String,
    @SerializedName("audio_url")
    val audioUrl: String,
    @SerializedName("text")
    val text: String
)
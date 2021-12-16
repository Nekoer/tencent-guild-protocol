package com.hcyacg.protocol.event

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg


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
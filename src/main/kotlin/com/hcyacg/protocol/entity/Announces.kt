package com.hcyacg.protocol.entity

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg


@NoArg
data class Announces(
    @SerializedName("guild_id")
    val guildId: String,
    @SerializedName("channel_id")
    val channelId: String,
    @SerializedName("message_id")
    val messageId: String,
)
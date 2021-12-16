package com.hcyacg.protocol.entity

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import com.hcyacg.protocol.event.api.MessageArk
import com.hcyacg.protocol.event.api.MessageAttachment
import com.hcyacg.protocol.event.api.MessageEmbed


@NoArg
data class Message(
    @SerializedName("id")
    val id: String,
    @SerializedName("channel_id")
    val channelId: String,
    @SerializedName("guild_id")
    val guildId: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("timestamp")
//    @Serializable(with = LocalDateTimeSerializer::class)
    val timestamp: String,
    @SerializedName("edited_timestamp")
//    @Serializable(with = LocalDateTimeSerializer::class)
    val editedTimestamp: String,
    @SerializedName("mention_everyone")
    val mentionEveryone: Boolean,
    @SerializedName("author")
    val author: User,
    @SerializedName("attachments")
    val attachments: MessageAttachment,
    @SerializedName("embeds")
    val embeds: MessageEmbed,
    @SerializedName("mentions")
    val mentions: User,
    @SerializedName("member")
    val member: Member,
    @SerializedName("ark")
    val ark: MessageArk
)
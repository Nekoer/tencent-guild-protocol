package com.hcyacg.protocol.entity

import com.hcyacg.protocol.anno.NoArg
import com.hcyacg.protocol.event.api.MessageArk
import com.hcyacg.protocol.event.api.MessageAttachment
import com.hcyacg.protocol.event.api.MessageEmbed
import com.hcyacg.protocol.utils.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
@NoArg
data class Message(
    @SerialName("id")
    val id: String,
    @SerialName("channel_id")
    val channel_id: String,
    @SerialName("guild_id")
    val guild_id: String,
    @SerialName("content")
    val content: String,
    @SerialName("timestamp")
//    @Serializable(with = LocalDateTimeSerializer::class)
    val timestamp: String,
    @SerialName("edited_timestamp")
//    @Serializable(with = LocalDateTimeSerializer::class)
    val edited_timestamp: String,
    @SerialName("mention_everyone")
    val mention_everyone: Boolean,
    @SerialName("author")
    val author: User,
    @SerialName("attachments")
    val attachments: MessageAttachment,
    @SerialName("embeds")
    val embeds: MessageEmbed,
    @SerialName("mentions")
    val mentions: User,
    @SerialName("member")
    val member: Member,
    @SerialName("ark")
    val ark: MessageArk
)
package com.hcyacg.protocol.event

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import com.hcyacg.protocol.event.api.Author
import com.hcyacg.protocol.event.api.Member

@NoArg
data class AtMessageCreateEvent(
    @SerializedName("attachments")
    var attachments: List<Attachment> = mutableListOf(),
    @SerializedName("author")
    val author: Author,
    @SerializedName("channel_id")
    val channelId: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("guild_id")
    val guildId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("member")
    val member: Member,
    @SerializedName("mentions")
    val mentions: List<Author>,
    @SerializedName("timestamp")
//    (with = LocalDateTimeSerializer::class)
    val timestamp: String
) {
    fun messageContent(): String {
        return content.replace(Regex("<@!\\d+>"), "")
    }
}
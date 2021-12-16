package com.hcyacg.protocol.event

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import com.hcyacg.protocol.event.api.Author
import com.hcyacg.protocol.event.api.Member
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class MessageCreateEvent(
    @SerializedName("attachments")
    var attachments: List<Attachment> = mutableListOf(),
    @SerializedName("author")
    var author: Author,
    @SerializedName("channel_id")
    var channelId: String = "",
    @SerializedName("content")
    var content: String = "",
    @SerializedName("guild_id")
    var guildId: String = "",
    @SerializedName("id")
    var id: String = "",
    @SerializedName("member")
    var member: Member,
    @SerializedName("timestamp")
    var timestamp: String = ""
)

/**
 * 附件 例：图片
 */
@NoArg
@Serializable
data class Attachment(
    @SerializedName("content_type")
    var content_type: String = "",
    @SerializedName("filename")
    var filename: String = "",
    @SerializedName("height")
    var height: Int = 0,
    @SerializedName("id")
    var id: String = "",
    @SerializedName("size")
    var size: Int = 0,
    @SerializedName("url")
    var url: String = "",
    @SerializedName("width")
    var width: Int = 0
)

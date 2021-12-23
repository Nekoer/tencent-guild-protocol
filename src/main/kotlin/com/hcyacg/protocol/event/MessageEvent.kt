package com.hcyacg.protocol.event

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import com.hcyacg.protocol.event.api.Author
import com.hcyacg.protocol.event.api.Member

@NoArg
open class MessageEvent {
    @SerializedName("attachments")
    var attachments: List<Attachment> = mutableListOf()

    @SerializedName("author")
    lateinit var author: Author

    @SerializedName("channel_id")
    lateinit var channelId: String

    @SerializedName("content")
    lateinit var content: String

    @SerializedName("guild_id")
    lateinit var guildId: String

    @SerializedName("id")
    lateinit var id: String

    @SerializedName("member")
    lateinit var member: Member

    @SerializedName("mentions")
    lateinit var mentions: List<Author>

    @SerializedName("timestamp")
    lateinit var timestamp: String

}

/**
 * 附件 例：图片
 */
@NoArg
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

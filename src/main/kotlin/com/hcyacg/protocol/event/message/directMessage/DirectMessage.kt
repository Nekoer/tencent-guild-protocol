package com.hcyacg.protocol.event.message.directMessage

import com.hcyacg.protocol.anno.NoArg
import com.google.gson.annotations.SerializedName


@NoArg
open class DirectMessage{
    @SerializedName("author")
    lateinit var author: Author
    @SerializedName("channel_id")
    lateinit var channelId: String
    @SerializedName("content")
    lateinit var content: String
    @SerializedName("direct_message")
    var directMessage: Boolean = true
    @SerializedName("guild_id")
    lateinit var guildId: String
    @SerializedName("id")
    lateinit var id: String
    @SerializedName("member")
    lateinit var member: Member
    @SerializedName("seq")
    var seq: Int = 0
    @SerializedName("timestamp")
    lateinit var timestamp: String

    fun isContentInitialized(): Boolean {
        return this::content.isInitialized
    }
}

data class Author(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("username")
    val username: String
)

data class Member(
    @SerializedName("joined_at")
    val joinedAt: String
)
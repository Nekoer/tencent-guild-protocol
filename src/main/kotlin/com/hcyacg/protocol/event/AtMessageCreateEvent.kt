package com.hcyacg.protocol.event

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import com.hcyacg.protocol.event.api.Author
import com.hcyacg.protocol.event.api.Member
import com.hcyacg.protocol.utils.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@NoArg
@Serializable
data class AtMessageCreateEvent(
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
//    @Serializable(with = LocalDateTimeSerializer::class)
    val timestamp: String
) {
    fun messageContent(): String {
        return content.replace(Regex("<@!\\d+>"), "")
    }
}
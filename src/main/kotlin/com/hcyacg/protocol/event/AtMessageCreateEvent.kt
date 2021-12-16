package com.hcyacg.protocol.event

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
    @SerialName("author")
    val author: Author,
    @SerialName("channel_id")
    val channel_id: String,
    @SerialName("content")
    val content: String,
    @SerialName("guild_id")
    val guild_id: String,
    @SerialName("id")
    val id: String,
    @SerialName("member")
    val member: Member,
    @SerialName("mentions")
    val mentions: List<Author>,
    @SerialName("timestamp")
    @Serializable(with = LocalDateTimeSerializer::class)
    val timestamp: LocalDateTime
) {
    fun messageContent(): String {
        return content.replace(Regex("<@!\\d+>"), "")
    }
}
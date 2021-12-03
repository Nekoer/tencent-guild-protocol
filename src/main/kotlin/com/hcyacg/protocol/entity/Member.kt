package com.hcyacg.protocol.entity

import com.hcyacg.protocol.anno.NoArg
import com.hcyacg.protocol.utils.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
@NoArg
data class Member(
    @SerialName("user")
    val user: User,
    @SerialName("nick")
    val nick: String,
    @SerialName("joined_at")
    val joined_at: String,
    @SerialName("roles")
    val roles: List<String>,
    @SerialName("deaf")
    val deaf: Boolean,
    @SerialName("mute")
    val mute: Boolean,
    @SerialName("pending")
    val pending: Boolean
)

@Serializable
@NoArg
data class MemberWithGuildID(
    @SerialName("guild_id")
    val guild_id: String,
    @SerialName("user")
    val user: User,
    @SerialName("nick")
    val nick: String,
    @SerialName("joined_at")
    @Serializable(with = LocalDateTimeSerializer::class)
    val joined_at: LocalDateTime,
    @SerialName("roles")
    val roles: List<String>
)
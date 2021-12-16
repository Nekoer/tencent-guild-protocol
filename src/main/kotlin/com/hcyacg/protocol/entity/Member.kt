package com.hcyacg.protocol.entity

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import com.hcyacg.protocol.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
@NoArg
data class Member(
    @SerializedName("user")
    val user: User?,
    @SerializedName("nick")
    val nick: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    @SerializedName("joined_at")
    val joinedAt: LocalDateTime,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("deaf")
    val deaf: Boolean,
    @SerializedName("mute")
    val mute: Boolean,
    @SerializedName("pending")
    val pending: Boolean
)

@Serializable
@NoArg
data class MemberWithGuildID(
    @SerializedName("guild_id")
    val guildId: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("nick")
    val nick: String,
    @SerializedName("joined_at")
    @Serializable(with = LocalDateTimeSerializer::class)
    val joinedAt: LocalDateTime,
    @SerializedName("roles")
    val roles: List<String>
)
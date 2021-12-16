package com.hcyacg.protocol.entity

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg


@NoArg
data class Member(
    @SerializedName("user")
    val user: User?,
    @SerializedName("nick")
    val nick: String,
    @SerializedName("joined_at")
    val joinedAt: String,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("deaf")
    val deaf: Boolean,
    @SerializedName("mute")
    val mute: Boolean,
    @SerializedName("pending")
    val pending: Boolean
)


@NoArg
data class MemberWithGuildID(
    @SerializedName("guild_id")
    val guildId: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("nick")
    val nick: String,
    @SerializedName("joined_at")
    val joinedAt: String,
    @SerializedName("roles")
    val roles: List<String>
)
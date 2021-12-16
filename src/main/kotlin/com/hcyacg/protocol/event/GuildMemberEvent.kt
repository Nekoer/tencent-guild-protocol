package com.hcyacg.protocol.event

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import com.hcyacg.protocol.event.api.User
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@NoArg
@Serializable
data class GuildMemberEvent(
    @SerializedName("guild_id")
    val guildId: String,
    @SerializedName("joined_at")
    val joinedAt: String,
    @SerializedName("nick")
    val nick: String,
    @SerializedName("op_user_id")
    val opUserId: String,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("user")
    val user: User
)


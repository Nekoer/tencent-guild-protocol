package com.hcyacg.protocol.event
import com.hcyacg.protocol.anno.NoArg
import com.hcyacg.protocol.event.api.User
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName
@NoArg
@Serializable
data class GuildMemberEvent(
    @SerialName("guild_id")
    val guild_id: String,
    @SerialName("joined_at")
    val joined_at: String,
    @SerialName("nick")
    val nick: String,
    @SerialName("op_user_id")
    val op_user_id: String,
    @SerialName("roles")
    val roles: List<String>,
    @SerialName("user")
    val user: User
)


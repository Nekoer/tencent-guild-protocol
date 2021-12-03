package com.hcyacg.protocol.event
import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@NoArg
@Serializable
data class GuildEvent(
    @SerialName("description")
    val description: String,
    @SerialName("icon")
    val icon: String,
    @SerialName("id")
    val id: String,
    @SerialName("joined_at")
    val joined_at: String,
    @SerialName("max_members")
    val max_members: Int,
    @SerialName("member_count")
    val member_count: Int,
    @SerialName("name")
    val name: String,
    @SerialName("op_user_id")
    val op_user_id: String,
    @SerialName("owner_id")
    val owner_id: String
)
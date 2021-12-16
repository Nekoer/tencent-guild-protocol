package com.hcyacg.protocol.event

import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
@NoArg
data class ChannelEvent(
    @SerialName("guild_id")
    val guild_id: String,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("op_user_id")
    val op_user_id: String,
    @SerialName("owner_id")
    val owner_id: String,
    @SerialName("sub_type")
    val sub_type: Int,
    @SerialName("type")
    val type: Int
)
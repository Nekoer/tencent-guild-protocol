package com.hcyacg.protocol.event

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg


@NoArg
data class ChannelEvent(
    @SerializedName("guild_id")
    val guildId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("op_user_id")
    val opUserId: String,
    @SerializedName("owner_id")
    val ownerId: String,
    @SerializedName("sub_type")
    val subType: Int,
    @SerializedName("type")
    val type: Int
)
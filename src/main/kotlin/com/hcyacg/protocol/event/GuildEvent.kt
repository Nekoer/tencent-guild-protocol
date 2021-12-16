package com.hcyacg.protocol.event

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@NoArg
@Serializable
data class GuildEvent(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("joined_at")
    val joinedAt: String,
    @SerializedName("max_members")
    val maxMembers: Int,
    @SerializedName("member_count")
    val memberCount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("op_user_id")
    val opUserId: String,
    @SerializedName("owner_id")
    val ownerId: String
)
package com.hcyacg.protocol.entity

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import com.hcyacg.protocol.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
@NoArg
data class Guild(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("owner_id")
    val ownerId: String,
    @SerializedName("owner")
    val owner: Boolean,
    @SerializedName("member_count")
    val memberCount: Int,
    @SerializedName("max_members")
    val maxMembers: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("joined_at")
    @Serializable(with = LocalDateTimeSerializer::class)
    val joinedAt: LocalDateTime,
)
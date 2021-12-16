package com.hcyacg.protocol.entity

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class Role(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("color")
    val color: Long,
    @SerializedName("hoist")
    val hoist: Long,
    @SerializedName("number")
    val number: Long,
    @SerializedName("number_limit")
    val numberLimit: Int,
)
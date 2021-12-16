package com.hcyacg.protocol.event.api

import com.google.gson.annotations.SerializedName


data class Member(
    @SerializedName("joined_at")
    val joinedAt: String,
    @SerializedName("roles")
    val roles: List<String>
)

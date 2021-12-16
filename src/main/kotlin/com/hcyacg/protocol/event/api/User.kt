package com.hcyacg.protocol.event.api

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerializedName("bot")
    val bot: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("username")
    val username: String
)
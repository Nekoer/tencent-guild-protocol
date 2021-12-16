package com.hcyacg.protocol.event.api

import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("bot")
    val bot: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("username")
    val username: String
)
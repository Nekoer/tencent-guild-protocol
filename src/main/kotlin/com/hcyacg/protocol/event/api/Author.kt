package com.hcyacg.protocol.event.api

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class Author(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("bot")
    val bot: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("username")
    val username: String
)
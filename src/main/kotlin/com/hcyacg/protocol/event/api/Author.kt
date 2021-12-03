package com.hcyacg.protocol.event.api

import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class Author(
    @SerialName("avatar")
    val avatar: String,
    @SerialName("bot")
    val bot: Boolean,
    @SerialName("id")
    val id: String,
    @SerialName("username")
    val username: String
)
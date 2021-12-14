package com.hcyacg.protocol.entity

import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class User(
    @SerialName("bot")
    val bot: Boolean?,
    @SerialName("id")
    val id: String?,
    @SerialName("username")
    val username: String?,
    @SerialName("avatar")
    val avatar: String?,
    @SerialName("union_openid")
    val union_openid: String?,
    @SerialName("union_user_account")
    val union_user_account: String?,
)
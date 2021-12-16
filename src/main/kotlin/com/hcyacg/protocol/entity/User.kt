package com.hcyacg.protocol.entity

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class User(
    @SerializedName("bot")
    var bot: Boolean?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("union_openid")
    val unionOpenid: String?,
    @SerializedName("union_user_account")
    val unionUserAccount: String?,
)
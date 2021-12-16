package com.hcyacg.protocol.entity

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class ChannelPermissions(
    @SerializedName("channel_id")
    val channelId: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("permissions")
    val permissions: String
)

@Serializable
class Permissions {
    companion object {
        /**
         * 可查看子频道
         */
        const val CHANNEL_VIEWABLE: Long = 1 shl 0

        /**
         * 可管理子频道
         */
        const val CHANNEL_MANAGEABLE: Long = 1 shl 1
    }
}
package com.hcyacg.protocol.entity

import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class ChannelPermissions(
    @SerialName("channel_id")
    val channel_id: String,
    @SerialName("user_id")
    val user_id: String,
    @SerialName("permissions")
    val permissions: String
)

@Serializable
 class Permissions{
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
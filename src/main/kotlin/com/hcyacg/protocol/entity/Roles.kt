package com.hcyacg.protocol.entity

import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class Roles (
    @SerialName("guild_id")
    val guild_id:String,
    @SerialName("roles")
    val roles:List<Role>,
    @SerialName("number_limit")
    val number_limit:Int,
)

@Serializable
@NoArg
data class Filter (
    @SerialName("name")
    val name:Long,
    @SerialName("color")
    val color:Long,
    @SerialName("hoist")
    val hoist:Long,
)

@Serializable
@NoArg
data class Info (
    @SerialName("name")
    val name:String,
    @SerialName("color")
    val color:Long,
    @SerialName("hoist")
    val hoist:Long,
)

@Serializable
@NoArg
data class RoleVo (
    @SerialName("role_id")
    val role_id:Long,
    @SerialName("role")
    val role:Role,
)

@Serializable
@NoArg
data class RoleDto (
    @SerialName("filter")
    val filter: Filter,
    @SerialName("info")
    val info: Info,
)

/**
 * 系统自带的身份组
 */
@Serializable
class DefaultRoles{
    companion object {
        /**
         * 全体成员
         */
        const val ALL_MEMBER: Long = 1

        /**
         * 管理员
         */
        const val ADMINISTRATOR: Long = 2

        /**
         * 群主/创建者
         */
        const val CREATOR: Long = 4

        /**
         * 子频道管理员
         */
        const val CHANNEL_MANAGER: Long = 5
    }
}

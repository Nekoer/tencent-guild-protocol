package com.hcyacg.protocol.entity

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg


@NoArg
data class Roles(
    @SerializedName("guild_id")
    val guildId: String,
    @SerializedName("roles")
    val roles: List<Role>,
    @SerializedName("number_limit")
    val numberLimit: Int,
)

/**
 *@param  name    是否修改名称: 0-否, 1-是
 *@param  color 是否修改颜色: 0-否, 1-是
 *@param  hoist    是否修改在成员列表中单独展示: 0-否, 1-是
 */

@NoArg
data class Filter(
    @SerializedName("name")
    val name: Long,
    @SerializedName("color")
    val color: Long,
    @SerializedName("hoist")
    val hoist: Long,
)

/**
 *@param name    名称
 *@param color    ARGB的HEX十六进制颜色值转换后的十进制数值
 *@param hoist    在成员列表中单独展示: 0-否, 1-是
 */

@NoArg
data class Info(
    @SerializedName("name")
    val name: String,
    @SerializedName("color")
    val color: Long,
    @SerializedName("hoist")
    val hoist: Long,
)



@NoArg
data class RoleVo(
    @SerializedName("role_id")
    val roleId: Long,
    @SerializedName("role")
    val role: Role,
)

/**
 * @param filter 标识需要修改哪些字段
 * @param info 携带需要修改的字段内容
 */

@NoArg
data class RoleDto(
    @SerializedName("filter")
    val filter: Filter,
    @SerializedName("info")
    val info: Info,
)

/**
 * 系统自带的身份组
 */

class DefaultRoles {
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

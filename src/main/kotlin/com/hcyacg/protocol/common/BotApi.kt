package com.hcyacg.protocol.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hcyacg.protocol.constant.Constant.Companion.botToken
import com.hcyacg.protocol.constant.Constant.Companion.logger
import com.hcyacg.protocol.constant.Constant.Companion.proUrl
import com.hcyacg.protocol.entity.*
import com.hcyacg.protocol.entity.Member
import com.hcyacg.protocol.entity.Message
import com.hcyacg.protocol.entity.User
import com.hcyacg.protocol.utils.OkHttpUtils
import okhttp3.Headers

object BotApi {

    private const val userMe = "$proUrl/users/@me"
    private const val userMeGuild = "$userMe/guilds"

    private const val channel = "$proUrl/channels/{{channel_id}}"
    private const val channelPermissions = "$channel/members/{{user_id}}/permissions"
    private const val sendMessage = "$channel/messages"
    private const val getMessage = "$channel/messages/{{message_id}}"
    private const val sendAudio = "$channel/audio"

    private const val channelRolePermissions = "$channel/roles/{{role_id}}/permissions"

    private const val guildInfo = "$proUrl/guilds/{{guild_id}}"
    private const val channelList = "$guildInfo/channels"
    private const val memberInfo = "$guildInfo/members/{{user_id}}"
    private const val memberMute = "$guildInfo/members/{{user_id}}/mute"

    private const val memberList = "$guildInfo/members"
    private const val roles = "$guildInfo/roles"
    private const val changeRole = "$guildInfo/roles/{{role_id}}"
    private const val editMemberRole = "$guildInfo/members/{{user_id}}/roles/{{role_id}}"

    private const val guildAnnounce = "$proUrl/guilds/{{guild_id}}/announces"
    private const val deleteGuildAnnounce = "$guildAnnounce/{{message_id}}"
    private const val channelAnnounces = "$proUrl/channels/{{channel_id}}/announces"
    private const val deleteChannelAnnounces = "$channelAnnounces/{{message_id}}"

    private const val schedules = "$proUrl/channels/{{channel_id}}/schedules"
    private const val scheduleUrl = "$schedules/{{schedule_id}}"

    private const val mute = "$proUrl/guilds/{{guild_id}}/mute"


    private fun officeApiHeader(): MutableMap<String, String> {
        return mutableMapOf(
            "Authorization" to botToken!!
        )
    }


    /**
     * 获取频道信息
     * @param guildId  频道id
     */
    @JvmStatic
    fun getGuildById(guildId: String): Guild {
        val url = guildInfo.replace("{{guild_id}}", guildId)

        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Guild::class.java)
    }


    /**
     * 获取频道身份组列表
     * @param guildId  频道id
     */
    @JvmStatic
    fun getRolesByGuild(guildId: String): Roles {
        val url = roles.replace("{{guild_id}}", guildId)
        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Roles::class.java)
    }

    /**
     * 创建频道身份组
     * @param guildId  频道id
     * @param filter 标识需要修改哪些字段
     * @param info 携带需要修改的字段内容
     */
    @JvmStatic
    fun createRolesByGuild(guildId: String, filter: Filter, info: Info): RoleVo {
        val url = roles.replace("{{guild_id}}", guildId)

        val json = Gson().toJson(RoleDto(filter, info))
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, RoleVo::class.java)
    }

    /**
     * 修改频道身份组
     * @param guildId  频道id
     * @param roleId 身份组id
     * @param filter 标识需要修改哪些字段
     * @param info 携带需要修改的字段内容
     */
    @JvmStatic
    fun changeRolesByGuild(guildId: String, roleId: String, filter: Filter, info: Info): RoleVo {
        val url = changeRole.replace("{{guild_id}}", guildId).replace("{{role_id}}", roleId)
        val json = Gson().toJson(RoleDto(filter, info))
        val res = OkHttpUtils.patchJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, RoleVo::class.java)
    }

    /**
     * 删除频道身份组
     * @param guildId  频道id
     * @param roleId 身份组id
     */
    @JvmStatic
    fun deleteRolesByGuild(guildId: String, roleId: String): Boolean {
        val url = changeRole.replace("{{guild_id}}", guildId).replace("{{role_id}}", roleId)

        val res = OkHttpUtils.delete(url, mutableMapOf(), officeApiHeader())
        logger.debug(res.body!!.string())
        return res.code == 204
    }


    /**
     * 增加频道身份组成员(除子频道管理员
     * @param guildId  频道id
     * @param userId 用户id
     * @param roleId 身份组id
     */
    @JvmStatic
    fun createMemberRolesByGuild(guildId: String, userId: String, roleId: String): Boolean {
        val url = editMemberRole.replace("{{guild_id}}", guildId).replace("{{user_id}}", userId)
            .replace("{{role_id}}", roleId)
        val res = OkHttpUtils.put(url, mutableMapOf(), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 204
    }

    /**
     * 增加频道身份组成员(仅子频道管理员
     * @param guildId  频道id
     * @param channel Channel对象
     * @param userId 用户id
     * @param roleId 身份组id
     */
    @JvmStatic
    fun createChildMemberRolesByGuild(guildId: String, channel: Channel, userId: String, roleId: String): Boolean {
        val url = editMemberRole.replace("{{guild_id}}", guildId).replace("{{user_id}}", userId)
            .replace("{{role_id}}", roleId)
        val json = Gson().toJson(channel)
        val res = OkHttpUtils.put(url, OkHttpUtils.addJson(json), Headers.headersOf("Authorization", botToken!!))
        logger.debug(res.code.toString())
        return res.code == 204
    }

    /**
     * 删除频道身份组成员(除子频道管理员
     * @param guildId  频道id
     * @param userId 用户id
     * @param roleId 身份组id
     */
    @JvmStatic
    fun deleteMemberRolesByGuild(guildId: String, userId: String, roleId: String): Boolean {
        val url = editMemberRole.replace("{{guild_id}}", guildId).replace("{{user_id}}", userId)
            .replace("{{role_id}}", roleId)
        val res = OkHttpUtils.delete(url, mutableMapOf(), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 204
    }

    /**
     * 删除频道身份组成员(仅子频道管理员
     * @param guildId  频道id
     * @param channel Channel对象
     * @param userId 用户id
     * @param roleId 身份组id
     */
    @JvmStatic
    fun deleteChildMemberRolesByGuild(guildId: String, channel: Channel, userId: String, roleId: String): Boolean {
        val url = editMemberRole.replace("{{guild_id}}", guildId).replace("{{user_id}}", userId)
            .replace("{{role_id}}", roleId)
        val json = Gson().toJson(channel)
        val res = OkHttpUtils.delete(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 204
    }

    /**
     * 获取单个成员消息
     * @param guildId  频道id
     * @param userId 用户id
     */
    @JvmStatic
    fun getMemberInfo(guildId: String, userId: String): Member {
        val url = memberInfo.replace("{{guild_id}}", guildId).replace("{{user_id}}", userId)

        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Member::class.java)
    }

    /**
     * 获取子频道消息
     * @param channelId 子频道id
     */
    @JvmStatic
    fun getChannelInfo(channelId: String): Channel {
        val url = channel.replace("{{channel_id}}", channelId)
        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Channel::class.java)
    }

    /**
     * 获取频道下的子频道列表
     * @param guildId  频道id
     */
    @JvmStatic
    fun getChildChannelInfo(guildId: String): List<Channel> {
        val url = channelList.replace("{{guild_id}}", guildId)

        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, object : TypeToken<List<Channel>>() {}.type)
    }

    /**
     * 根据message的id获取消息数据
     * @param channelId 子频道id
     * @param messageId 消息id
     */
    @JvmStatic
    fun getMessageById(channelId: String, messageId: String): Message {
        val url = getMessage.replace("{{channel_id}}", channelId).replace("{{message_id}}", messageId)

        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }

    /**
     * 获取指定子频道的权限
     * @param channelId 子频道id
     * @param userId 用户id
     */
    @JvmStatic
    fun getChannelPermissions(channelId: String, userId: String): ChannelPermissions {
        val url = channelPermissions.replace("{{channel_id}}", channelId).replace("{{user_id}}", userId)
        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, ChannelPermissions::class.java)
    }

    /**
     * 修改指定子频道的权限 目前只支持修改查看权限
     * @param channelId 子频道id
     * @param userId 用户id
     * @param isAdd 是否赋予用户查看子频道权限
     */
    @JvmStatic
    fun changeChannelPermissions(channelId: String, userId: String, isAdd: Boolean): Boolean {
        val url = channelPermissions.replace("{{channel_id}}", channelId).replace("{{user_id}}", userId)
        val map = mutableMapOf<String, String>()
        if (isAdd) {
            map["add"] = 0.shl(1).toString()
        } else {
            map["remove"] = 1.shl(1).toString()
        }
        val res = OkHttpUtils.put(url, map, officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 204
    }
    
    /**
     * 获取指定子频道身份组的权限
     * @param channelId 子频道id
     * @param roleId 身份组id
     */
    @JvmStatic
    fun getChannelRolePermissions(channelId: String, roleId: String): ChannelPermissions {
        val url = channelRolePermissions.replace("{{channel_id}}", channelId).replace("{{role_id}}", roleId)
        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, ChannelPermissions::class.java)
    }

    /**
     * 修改指定子频道身份组的权限 目前只支持修改查看权限
     * @param channelId 子频道id
     * @param roleId 身份组id
     * @param isAdd 是否赋予用户查看子频道权限
     */
    @JvmStatic
    fun changeChannelRolePermissions(channelId: String, roleId: String, isAdd: Boolean): Boolean {
        val url = channelRolePermissions.replace("{{channel_id}}", channelId).replace("{{role_id}}", roleId)
        val map = mutableMapOf<String, String>()
        if (isAdd) {
            map["add"] = 0.shl(1).toString()
        } else {
            map["remove"] = 1.shl(1).toString()
        }
        val res = OkHttpUtils.put(url, map, officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 204
    }


    /**
     * 获取当前用户信息
     */
    @JvmStatic
    fun getMe(): User {
        val res = OkHttpUtils.getJson(userMe, officeApiHeader())
        logger.debug(res)
        val user = Gson().fromJson(res, User::class.java)
        user.bot = true
        return user
    }

    /**
     * 获取当前用户频道列表
     * guildId	string	读此id之前的数据	guild id, before/after 只能带一个
     * limit	int	每次拉取多少条数据	最大不超过100，默认100
     */
    @JvmStatic
    fun getMeGuildsBefore(guildId: String, limit: Int): List<Guild> {
        val url = userMeGuild.plus("?before=$guildId").plus("&limit=$limit")
        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, object : TypeToken<List<Guild>>() {}.type)
    }

    /**
     * 获取当前用户频道列表
     * guildId	string	读此id之后的数据	guild id, before/after 只能带一个
     * limit	int	每次拉取多少条数据	最大不超过100，默认100
     */
    @JvmStatic
    fun getMeGuildsAfter(guildId: String, limit: Int): List<Guild> {
        val url = userMeGuild.plus("?after=$guildId").plus("&limit=$limit")
        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, object : TypeToken<List<Guild>>() {}.type)
    }

    /**
     * 私域功能
     */

    /**
     * 获取频道成员列表
     * @param guildId  频道id
     * @param userId 上一次回包中最大的用户ID， 如果是第一次请求填0，默认为0
     * @param limit 分页大小，1-1000，默认是1
     */
    @JvmStatic
    fun getGuildMemberList(guildId: String, userId: String, limit: Int): List<Member> {
        val url = memberList.replace("{{guild_id}}", guildId)
        url.plus("?after=$userId").plus("&limit=$limit")
        val res = OkHttpUtils.getJson(url, Headers.headersOf("Authorization", botToken!!))
        logger.debug(res)
        return Gson().fromJson(res, object : TypeToken<List<Member>>() {}.type)
    }

    /**
     * 删除指定频道成员
     * @param guildId 频道id
     * @param userId 用户id
     */
    @JvmStatic
    fun deleteMember(guildId: String, userId: String): Boolean {
        val url = memberInfo.replace("{{guild_id}}", guildId).replace("{{user_id}}", userId)
        val res = OkHttpUtils.delete(url, mutableMapOf(), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 204
    }

    /**
     * 创建子频道
     * @param guildId  频道id
     * @param channelDto 创建子频道需要的参数对象
     */
    @JvmStatic
    fun createChannel(guildId: String, channelDto: ChannelDto): Channel {
        val url = channelList.replace("{{guild_id}}", guildId)
        val json = Gson().toJson(channelDto)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Channel::class.java)
    }

    /**
     * 修改子频道信息
     * @param channelId  子频道id
     * @param channelDto 创建子频道需要的参数对象
     */
    @JvmStatic
    fun changeChannelInfo(channelId: String, channelDto: ChannelDto): Channel {
        val url = channel.replace("{{channel_id}}", channelId)
        val json = Gson().toJson(channelDto)
        val res = OkHttpUtils.patchJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Channel::class.java)
    }

    /**
     * 删除子频道
     * @param channelId 子频道id
     */
    @JvmStatic
    fun deleteChannel(channelId: String): Boolean {
        val url = channel.replace("{{channel_id}}", channelId)
        val res = OkHttpUtils.delete(url, mutableMapOf(), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 200
    }


    /**
     * 创建全频道公告
     * @param guildId 频道id
     * @param channelId 子频道id
     * @param messageId 消息id
     */
    @JvmStatic
    fun createGuildAnnounces(guildId: String,channelId: String, messageId: String): Announces {
        val url = guildAnnounce.replace("{{guild_id}}", guildId)
        val json = "{\"message_id\": \"$messageId\",\"channel_id\":\"$channelId\"}"
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Announces::class.java)
    }

    /**
     * 删除全频道公告
     * @param guildId 频道id
     * @param messageId 消息id
     */
    @JvmStatic
    fun deleteGuildAnnounces(guildId: String, messageId: String): Boolean {
        val url = deleteGuildAnnounce.replace("{{guild_id}}", guildId).replace("{{message_id}}", messageId)
        val res = OkHttpUtils.delete(url, mutableMapOf(), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 204
    }


    /**
     * 创建子频道公告
     * @param channelId 子频道id
     * @param messageId 消息id
     */
    @JvmStatic
    fun createChannelAnnounces(channelId: String, messageId: String): Announces {
        val url = channelAnnounces.replace("{{channel_id}}", channelId)
        val json = "{\"message_id\": \"$messageId\"}"
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Announces::class.java)
    }

    /**
     * 删除子频道公告
     * @param channelId 子频道id
     * @param messageId 消息id
     */
    @JvmStatic
    fun deleteChannelAnnounces(channelId: String, messageId: String): Boolean {
        val url = deleteChannelAnnounces.replace("{{channel_id}}", channelId).replace("{{message_id}}", messageId)
        val res = OkHttpUtils.delete(url, mutableMapOf(), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 204
    }

    /**
     * 则返回结束时间在 since 之后的日程列表
     * @param channelId 子频道id
     * @param since 时间戳
     */
    @JvmStatic
    fun getScheduleListByTime(channelId: String, since: Long): List<Schedule> {
        val url = schedules.replace("{{channel_id}}", channelId)
        url.plus("?since=$since")
        val res = OkHttpUtils.getJson(url, Headers.headersOf("Authorization", botToken!!))
        logger.debug(res)
        if (res.contentEquals("null")) {
            return mutableListOf()
        }
        return Gson().fromJson(res, object : TypeToken<List<Schedule>>() {}.type)
    }

    /**
     * 返回当天的日程列表
     * @param channelId 子频道id
     */
    @JvmStatic
    fun getScheduleList(channelId: String): List<Schedule> {
        val url = schedules.replace("{{channel_id}}", channelId)
        val res = OkHttpUtils.getJson(url, Headers.headersOf("Authorization", botToken!!))
        logger.debug(res)
        if (res.contentEquals("null")) {
            return mutableListOf()
        }
        return Gson().fromJson(res, object : TypeToken<List<Schedule>>() {}.type)
    }

    /**
     * 获取单个日程信息
     * @param channelId 子频道id
     * @param scheduleId 日程id
     */
    @JvmStatic
    fun getScheduleById(channelId: String, scheduleId: String): Schedule {
        val url = scheduleUrl.replace("{{channel_id}}", channelId).replace("{{schedule_id}}", scheduleId)
        val res = OkHttpUtils.getJson(url, Headers.headersOf("Authorization", botToken!!))
        logger.debug(res)
        return Gson().fromJson(res, Schedule::class.java)
    }


    /**
     * 创建日程
     * 要求操作人具有管理频道的权限，如果是机器人，则需要将机器人设置为管理员。
     * 时间戳的值不允许创建开始时间早于现在的;
     * 结束时间戳不能超过创建开始的时间戳7天
     * @param channelId 子频道id
     * @param schedule 日程对象
     */
    @JvmStatic
    fun createSchedule(channelId: String, schedule: Schedule): Schedule {
        val url = schedules.replace("{{channel_id}}", channelId)
        schedule.creator = null
        val json = "{\"schedule\": ${Gson().toJson(schedule)}}"
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Schedule::class.java)
    }

    /**
     * 修改日程
     * 要求操作人具有管理频道的权限，如果是机器人，则需要将机器人设置为管理员。
     * @param channelId 子频道id
     * @param scheduleId 日程id
     * @param schedule 日程对象，不需要带id
     */
    @JvmStatic
    fun changeScheduleById(channelId: String, scheduleId: String, schedule: Schedule): Schedule {
        val url = scheduleUrl.replace("{{channel_id}}", channelId).replace("{{schedule_id}}", scheduleId)
        schedule.creator = null
        val json = "{\"schedule\": ${Gson().toJson(schedule)}}"
        val res = OkHttpUtils.patchJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Schedule::class.java)
    }

    /**
     * 删除日程
     * 要求操作人具有管理频道的权限，如果是机器人，则需要将机器人设置为管理员。
     * @param channelId 子频道id
     * @param scheduleId 日程id
     */
    @JvmStatic
    fun deleteScheduleById(channelId: String, scheduleId: String): Boolean {
        val url = scheduleUrl.replace("{{channel_id}}", channelId).replace("{{schedule_id}}", scheduleId)
        val res = OkHttpUtils.delete(url, mutableMapOf(), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 204
    }

}
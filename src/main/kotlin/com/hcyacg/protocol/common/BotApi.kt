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
import com.hcyacg.protocol.event.AtMessageCreateEvent
import com.hcyacg.protocol.event.MessageCreateEvent
import com.hcyacg.protocol.event.api.*
import com.hcyacg.protocol.utils.JsonUtils.objectToJson
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

    private const val guildInfo = "$proUrl/guilds/{{guild_id}}"
    private const val channelList = "$guildInfo/channels"
    private const val memberInfo = "$guildInfo/members/{{user_id}}"
    private const val memberList = "$guildInfo/members"
    private const val roles = "$guildInfo/roles"
    private const val changeRole = "$guildInfo/roles/{{role_id}}"
    private const val editMemberRole = "$guildInfo/members/{{user_id}}/roles/{{role_id}}"

    private const val announces = "$proUrl/channels/{{channel_id}}/announces"
    private const val deleteAnnounces = "$announces/{{message_id}}"

    private const val schedules = "$proUrl/channels/{{channel_id}}/schedules"
    private const val scheduleUrl = "$schedules/{{schedule_id}}"


    private fun officeApiHeader(): MutableMap<String, String> {
        return mutableMapOf(
            "Authorization" to botToken!!
        )
    }


    fun AtMessageCreateEvent.replyText(msg: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = TextMessage(msg, this.id).objectToJson()
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun AtMessageCreateEvent.replyTextNotId(msg: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeTextMessage(msg).objectToJson()
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun AtMessageCreateEvent.replyTextWithImage(msg: String, imageUrl: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = TextWithImageMessage(msg, imageUrl, this.id).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun AtMessageCreateEvent.replyTextWithImageNotId(msg: String, imageUrl: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeTextWithImageMessage(msg, imageUrl).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun AtMessageCreateEvent.replyImage(imageUrl: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = ImageMessage(imageUrl, this.id).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }


    fun AtMessageCreateEvent.replyImageNotId(imageUrl: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeImageMessage(imageUrl).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun AtMessageCreateEvent.replyArk(messageArk: MessageArk): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = ArkMessage(messageArk, this.id).objectToJson()
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun AtMessageCreateEvent.replyArkNotId(messageArk: MessageArk): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeArkMessage(messageArk).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun AtMessageCreateEvent.replyEmbed(messageEmbed: MessageEmbed): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = EmbedMessage(messageEmbed, this.id).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun AtMessageCreateEvent.replyEmbedNotId(messageEmbed: MessageEmbed): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeEmbedMessage(messageEmbed).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun AtMessageCreateEvent.replyAudio(audioUrl: String, text: String, status: Int): Message {
        val url = sendAudio.replace("{{channel_id}}", this.channel_id)
        val json = AudioControl(audioUrl, text, status).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }


    fun MessageCreateEvent.replyText(msg: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = TextMessage(msg, this.id).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun MessageCreateEvent.replyTextNotId(msg: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeTextMessage(msg).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun MessageCreateEvent.replyTextWithImage(msg: String, imageUrl: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = TextWithImageMessage(msg, imageUrl, this.id).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun MessageCreateEvent.replyTextWithImageNotId(msg: String, imageUrl: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeTextWithImageMessage(msg, imageUrl).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun MessageCreateEvent.replyImage(imageUrl: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = ImageMessage(imageUrl, this.id).objectToJson()
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }


    fun MessageCreateEvent.replyImageNotId(imageUrl: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeImageMessage(imageUrl).objectToJson()
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun MessageCreateEvent.replyArk(messageArk: MessageArk): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = ArkMessage(messageArk, this.id).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun MessageCreateEvent.replyArkNotId(messageArk: MessageArk): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeArkMessage(messageArk).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun MessageCreateEvent.replyEmbed(messageEmbed: MessageEmbed): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = EmbedMessage(messageEmbed, this.id).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    fun MessageCreateEvent.replyEmbedNotId(messageEmbed: MessageEmbed): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeEmbedMessage(messageEmbed).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    /**
     * 获取频道信息
     * @param guildId  频道id
     */
    fun getGuildById(guildId: String): Guild {
        val url = guildInfo.replace("{{guild_id}}", guildId)

        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Guild::class.java)
    }


    /**
     * 获取频道身份组列表
     * @param guildId  频道id
     */
    fun getRolesByGuild(guildId: String): Roles {
        val url = roles.replace("{{guild_id}}", guildId)
        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Roles::class.java)
    }

    /**
     * 创建频道身份组
     * @param guildId  频道id
     * @param filter 标识需要修改哪些字段
     * @param info 携带需要修改的字段内容
     */
    fun createRolesByGuild(guildId: String, filter: Filter, info: Info): RoleVo {
        val url = roles.replace("{{guild_id}}", guildId)

        val json = RoleDto(filter, info).objectToJson()
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), RoleVo::class.java)
    }

    /**
     * 修改频道身份组
     * @param guildId  频道id
     * @param roleId 身份组id
     * @param filter 标识需要修改哪些字段
     * @param info 携带需要修改的字段内容
     */
    fun changeRolesByGuild(guildId: String, roleId: String, filter: Filter, info: Info): RoleVo {
        val url = changeRole.replace("{{guild_id}}", guildId).replace("{{role_id}}", roleId)
        val json = RoleDto(filter, info).objectToJson()
        val res = OkHttpUtils.patchJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), RoleVo::class.java)
    }

    /**
     * 删除频道身份组
     * @param guildId  频道id
     * @param roleId 身份组id
     */
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
    fun createChildMemberRolesByGuild(guildId: String, channel: Channel, userId: String, roleId: String): Boolean {
        val url = editMemberRole.replace("{{guild_id}}", guildId).replace("{{user_id}}", userId)
            .replace("{{role_id}}", roleId)
        val json = channel.objectToJson()
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
    fun deleteChildMemberRolesByGuild(guildId: String, channel: Channel, userId: String, roleId: String): Boolean {
        val url = editMemberRole.replace("{{guild_id}}", guildId).replace("{{user_id}}", userId)
            .replace("{{role_id}}", roleId)
        val json = channel.objectToJson()
        val res = OkHttpUtils.delete(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 204
    }

    /**
     * 获取单个成员消息
     * @param guildId  频道id
     * @param userId 用户id
     */
    fun getMemberInfo(guildId: String, userId: String): Member {
        val url = memberInfo.replace("{{guild_id}}", guildId).replace("{{user_id}}", userId)

        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Member::class.java)
    }

    /**
     * 获取子频道消息
     * @param channelId 子频道id
     */
    fun getChannelInfo(channelId: String): Channel {
        val url = channel.replace("{{channel_id}}", channelId)
        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Channel::class.java)
    }

    /**
     * 获取频道下的子频道列表
     * @param guildId  频道id
     */
    fun getChildChannelInfo(guildId: String): List<Channel> {
        val url = channelList.replace("{{guild_id}}", guildId)

        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), object : TypeToken<List<Channel>>() {}.type)
    }

    /**
     * 根据message的id获取消息数据
     * @param channelId 子频道id
     * @param messageId 消息id
     */
    fun getMessageById(channelId: String, messageId: String): Message {
        val url = getMessage.replace("{{channel_id}}", channelId).replace("{{message_id}}", messageId)

        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    /**
     * 获取指定子频道的权限
     * @param channelId 子频道id
     * @param userId 用户id
     */
    fun getChannelPermissions(channelId: String, userId: String): ChannelPermissions {
        val url = channelPermissions.replace("{{channel_id}}", channelId).replace("{{user_id}}", userId)
        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), ChannelPermissions::class.java)
    }

    /**
     * 修改指定子频道的权限 目前只支持修改查看权限
     * @param channelId 子频道id
     * @param userId 用户id
     * @param isAdd 是否赋予用户查看子频道权限
     */
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
     * 获取当前用户信息
     */
    fun getMe(): User {
        val res = OkHttpUtils.getJson(userMe, officeApiHeader())
        logger.debug(res.toString())
        val user = Gson().fromJson(res.toString(), User::class.java)
        user.bot = true
        return user
    }

    /**
     * 获取当前用户频道列表
     * guildId	string	读此id之前的数据	guild id, before/after 只能带一个
     * limit	int	每次拉取多少条数据	最大不超过100，默认100
     */
    fun getMeGuildsBefore(guildId: String, limit: Int): List<Guild> {
        val url = userMeGuild.plus("?before=$guildId").plus("&limit=$limit")
        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), object : TypeToken<List<Guild>>() {}.type)
    }

    /**
     * 获取当前用户频道列表
     * guildId	string	读此id之后的数据	guild id, before/after 只能带一个
     * limit	int	每次拉取多少条数据	最大不超过100，默认100
     */
    fun getMeGuildsAfter(guildId: String, limit: Int): List<Guild> {
        val url = userMeGuild.plus("?after=$guildId").plus("&limit=$limit")
        val res = OkHttpUtils.getJson(url, officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), object : TypeToken<List<Guild>>() {}.type)
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
    fun getGuildMemberList(guildId: String, userId: String, limit: Int): List<Member> {
        val url = memberList.replace("{{guild_id}}", guildId)
        url.plus("?after=$userId").plus("&limit=$limit")
        val res = OkHttpUtils.getJson(url, Headers.headersOf("Authorization", botToken!!))
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), object : TypeToken<List<Member>>() {}.type)
    }

    /**
     * 删除指定频道成员
     * @param guildId 频道id
     * @param userId 用户id
     */
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
    fun createChannel(guildId: String, channelDto: ChannelDto): Channel {
        val url = channelList.replace("{{guild_id}}", guildId)
        val json = channelDto.objectToJson()
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Channel::class.java)
    }

    /**
     * 修改子频道信息
     * @param channelId  子频道id
     * @param channelDto 创建子频道需要的参数对象
     */
    fun changeChannelInfo(channelId: String, channelDto: ChannelDto): Channel {
        val url = channel.replace("{{channel_id}}", channelId)
        val json = channelDto.objectToJson()
        val res = OkHttpUtils.patchJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Channel::class.java)
    }

    /**
     * 删除子频道
     * @param channelId 子频道id
     */
    fun deleteChannel(channelId: String): Boolean {
        val url = channel.replace("{{channel_id}}", channelId)
        val res = OkHttpUtils.delete(url, mutableMapOf(), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 200
    }

    /**
     * 创建子频道公告
     * @param channelId 子频道id
     * @param messageId 消息id
     */
    fun createAnnounces(channelId: String, messageId: String): Announces {
        val url = announces.replace("{{channel_id}}", channelId)
        val json = "{\"message_id\": \"$messageId\"}"
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Announces::class.java)
    }

    /**
     * 删除子频道公告
     * @param channelId 子频道id
     * @param messageId 消息id
     */
    fun deleteAnnounces(channelId: String, messageId: String): Boolean {
        val url = deleteAnnounces.replace("{{channel_id}}", channelId).replace("{{message_id}}", messageId)
        val res = OkHttpUtils.delete(url, mutableMapOf(), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 200
    }

    /**
     * 则返回结束时间在 since 之后的日程列表
     * @param channelId 子频道id
     * @param since 时间戳
     */
    fun getScheduleListByTime(channelId: String, since: Long): List<Schedule> {
        val url = schedules.replace("{{channel_id}}", channelId)
        url.plus("?since=$since")
        val res = OkHttpUtils.getJson(url, Headers.headersOf("Authorization", botToken!!))
        logger.debug(res.toString())
        if (res.toString().contentEquals("null")) {
            return mutableListOf()
        }
        return Gson().fromJson(res.toString(), object : TypeToken<List<Schedule>>() {}.type)
    }

    /**
     * 返回当天的日程列表
     * @param channelId 子频道id
     */
    fun getScheduleList(channelId: String): List<Schedule> {
        val url = schedules.replace("{{channel_id}}", channelId)
        val res = OkHttpUtils.getJson(url, Headers.headersOf("Authorization", botToken!!))
        logger.debug(res.toString())
        if (res.toString().contentEquals("null")) {
            return mutableListOf()
        }
        return Gson().fromJson(res.toString(), object : TypeToken<List<Schedule>>() {}.type)
    }

    /**
     * 获取单个日程信息
     * @param channelId 子频道id
     * @param scheduleId 日程id
     */
    fun getScheduleById(channelId: String, scheduleId: String): Schedule {
        val url = scheduleUrl.replace("{{channel_id}}", channelId).replace("{{schedule_id}}", scheduleId)
        val res = OkHttpUtils.getJson(url, Headers.headersOf("Authorization", botToken!!))
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Schedule::class.java)
    }


    /**
     * 创建日程
     * 要求操作人具有管理频道的权限，如果是机器人，则需要将机器人设置为管理员。
     * 时间戳的值不允许创建开始时间早于现在的;
     * 结束时间戳不能超过创建开始的时间戳7天
     * @param channelId 子频道id
     * @param schedule 日程对象
     */
    fun createSchedule(channelId: String, schedule: Schedule): Schedule {
        val url = schedules.replace("{{channel_id}}", channelId)
        schedule.creator = null
        val json = "{\"schedule\": ${schedule.objectToJson()}}"
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Schedule::class.java)
    }

    /**
     * 修改日程
     * 要求操作人具有管理频道的权限，如果是机器人，则需要将机器人设置为管理员。
     * @param channelId 子频道id
     * @param scheduleId 日程id
     * @param schedule 日程对象，不需要带id
     */
    fun changeScheduleById(channelId: String, scheduleId: String, schedule: Schedule): Schedule {
        val url = scheduleUrl.replace("{{channel_id}}", channelId).replace("{{schedule_id}}", scheduleId)
        schedule.creator = null
        val json = "{\"schedule\": ${schedule.objectToJson()}}"
        val res = OkHttpUtils.patchJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.toString())
        return Gson().fromJson(res.toString(), Schedule::class.java)
    }

    /**
     * 删除日程
     * 要求操作人具有管理频道的权限，如果是机器人，则需要将机器人设置为管理员。
     * @param channelId 子频道id
     * @param scheduleId 日程id
     */
    fun deleteScheduleById(channelId: String, scheduleId: String): Boolean {
        val url = scheduleUrl.replace("{{channel_id}}", channelId).replace("{{schedule_id}}", scheduleId)
        val res = OkHttpUtils.delete(url, mutableMapOf(), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 204
    }

}
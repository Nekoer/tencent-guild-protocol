package com.hcyacg.protocol.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hcyacg.protocol.common.BotApi.replyEmbedNotId
import com.hcyacg.protocol.constant.Constant.Companion.botToken
import com.hcyacg.protocol.constant.Constant.Companion.proUrl
import com.hcyacg.protocol.entity.*
import com.hcyacg.protocol.entity.Member
import com.hcyacg.protocol.entity.Message
import com.hcyacg.protocol.event.AtMessageCreateEvent
import com.hcyacg.protocol.event.MessageCreateEvent
import com.hcyacg.protocol.event.api.*
import com.hcyacg.protocol.utils.JsonUtils.objectToJson
import com.hcyacg.protocol.utils.OkHttpUtils
import okhttp3.Headers
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object BotApi {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)


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
    private const val roles = "$guildInfo/roles"
    private const val changeRole = "$guildInfo/roles/{{role_id}}"
    private const val editMemberRole = "$guildInfo/members/{{user_id}}/roles/{{role_id}}"


    private fun officeApiHeader(): MutableMap<String, String> {
        return mutableMapOf(
            "Authorization" to botToken!!
        )
    }


    fun AtMessageCreateEvent.replyText(msg: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = TextMessage(msg, this.id).objectToJson()
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun AtMessageCreateEvent.replyTextNotId(msg: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeTextMessage(msg).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun AtMessageCreateEvent.replyTextWithImage(msg: String, imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = TextWithImageMessage(msg, imageUrl, this.id).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun AtMessageCreateEvent.replyTextWithImageNotId(msg: String, imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeTextWithImageMessage(msg, imageUrl).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun AtMessageCreateEvent.replyImage(imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = ImageMessage(imageUrl, this.id).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }


    fun AtMessageCreateEvent.replyImageNotId(imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeImageMessage(imageUrl).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun AtMessageCreateEvent.replyArk(messageArk: MessageArk): Boolean {
        println(Gson().toJson(messageArk))
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = ArkMessage(messageArk, this.id).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        println(res.toString())
        return true
    }

    fun AtMessageCreateEvent.replyArkNotId(messageArk: MessageArk): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeArkMessage(messageArk).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun AtMessageCreateEvent.replyEmbed(messageEmbed: MessageEmbed): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = EmbedMessage(messageEmbed, this.id).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun AtMessageCreateEvent.replyEmbedNotId(messageEmbed: MessageEmbed): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeEmbedMessage(messageEmbed).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun AtMessageCreateEvent.replyAudio(audioUrl: String, text: String, status: Int): Boolean {
        val url = sendAudio.replace("{{channel_id}}", this.channel_id)
        val json = AudioControl(audioUrl, text, status).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }


    fun MessageCreateEvent.replyText(msg: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = TextMessage(msg, this.id).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun MessageCreateEvent.replyTextNotId(msg: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeTextMessage(msg).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun MessageCreateEvent.replyTextWithImage(msg: String, imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = TextWithImageMessage(msg, imageUrl, this.id).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun MessageCreateEvent.replyTextWithImageNotId(msg: String, imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeTextWithImageMessage(msg, imageUrl).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun MessageCreateEvent.replyImage(imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = ImageMessage(imageUrl, this.id).objectToJson()
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }


    fun MessageCreateEvent.replyImageNotId(imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeImageMessage(imageUrl).objectToJson()
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun MessageCreateEvent.replyArk(messageArk: MessageArk): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = ArkMessage(messageArk, this.id).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun MessageCreateEvent.replyArkNotId(messageArk: MessageArk): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeArkMessage(messageArk).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun MessageCreateEvent.replyEmbed(messageEmbed: MessageEmbed): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = EmbedMessage(messageEmbed, this.id).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    fun MessageCreateEvent.replyEmbedNotId(messageEmbed: MessageEmbed): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeEmbedMessage(messageEmbed).objectToJson()

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return true
    }

    /**
     * 获取频道信息
     */
    fun getGuildById(guildId: String): Guild {
        val url = guildInfo.replace("{{guild_id}}", guildId)

        val res = OkHttpUtils.getJson(url, officeApiHeader())
        return Gson().fromJson(res.toString(), Guild::class.java)
    }


    /**
     * 获取频道身份组列表
     */
    fun getRolesByGuild(guildId: String): Roles {
        val url = roles.replace("{{guild_id}}", guildId)
        val res = OkHttpUtils.getJson(url, officeApiHeader())
        return Gson().fromJson(res.toString(), Roles::class.java)
    }

    /**
     * 创建频道身份组
     */
    fun createRolesByGuild(guildId: String, filter: Filter, info: Info): RoleVo {
        val url = roles.replace("{{guild_id}}", guildId)

        val json = RoleDto(filter, info).objectToJson()
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        return Gson().fromJson(res.toString(), RoleVo::class.java)
    }

    /**
     * 修改频道身份组
     */
    fun changeRolesByGuild(guildId: String, roleId: String, filter: Filter, info: Info): RoleVo {
        val url = changeRole.replace("{{guild_id}}", guildId).replace("{{role_id}}", roleId)
        val json = RoleDto(filter, info).objectToJson()
        val res = OkHttpUtils.patchJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        println(res)
        return Gson().fromJson(res.toString(), RoleVo::class.java)
    }

    /**
     * 删除频道身份组
     */
    fun deleteRolesByGuild(guildId: String, roleId: String): Boolean {
        val url = changeRole.replace("{{guild_id}}", guildId).replace("{{role_id}}", roleId)

        val res = OkHttpUtils.delete(url, mutableMapOf(), officeApiHeader())
        println(res.body?.string())
        return res.code == 204
    }


    /**
     * 增加频道身份组成员(除子频道管理员
     */
    fun createMemberRolesByGuild(guildId: String, userId: String, roleId: String): Boolean {
        val url = editMemberRole.replace("{{guild_id}}", guildId).replace("{{user_id}}", userId)
            .replace("{{role_id}}", roleId)
        val res = OkHttpUtils.put(url, mutableMapOf(), officeApiHeader())
        return res.code == 204
    }

    /**
     * 增加频道身份组成员(仅子频道管理员
     */
    fun createChildMemberRolesByGuild(guildId: String, channel: Channel, userId: String, roleId: String): Boolean {
        val url = editMemberRole.replace("{{guild_id}}", guildId).replace("{{user_id}}", userId)
            .replace("{{role_id}}", roleId)
        val json = channel.objectToJson()
        val res = OkHttpUtils.put(url, OkHttpUtils.addJson(json), Headers.headersOf("Authorization", botToken!!))
        println(res.body?.string())
        return res.code == 204
    }

    /**
     * 删除频道身份组成员(除子频道管理员
     */
    fun deleteMemberRolesByGuild(guildId: String, userId: String, roleId: String): Boolean {
        val url = editMemberRole.replace("{{guild_id}}", guildId).replace("{{user_id}}", userId)
            .replace("{{role_id}}", roleId)
        val res = OkHttpUtils.delete(url, mutableMapOf(), officeApiHeader())
        return res.code == 204
    }

    /**
     * 删除频道身份组成员(仅子频道管理员
     */
    fun deleteChildMemberRolesByGuild(guildId: String, channel: Channel, userId: String, roleId: String): Boolean {
        val url = editMemberRole.replace("{{guild_id}}", guildId).replace("{{user_id}}", userId)
            .replace("{{role_id}}", roleId)
        val json = channel.objectToJson()
        val res = OkHttpUtils.delete(url, OkHttpUtils.addJson(json), officeApiHeader())
        return res.code == 204
    }

    /**
     * 获取单个成员消息
     */
    fun getMemberInfo(guildId: String, userId: String): Member {
        val url = memberInfo.replace("{{guild_id}}", guildId).replace("{{user_id}}", userId)

        val res = OkHttpUtils.getJson(url, officeApiHeader())
        return Gson().fromJson(res.toString(), Member::class.java)
    }

    /**
     * 获取子频道消息
     */
    fun getChannelInfo(channelId: String): Channel {
        val url = channel.replace("{{channel_id}}", channelId)
        val res = OkHttpUtils.getJson(url, officeApiHeader())
        return Gson().fromJson(res.toString(), Channel::class.java)
    }

    /**
     * 获取频道下的子频道列表
     */
    fun getChildChannelInfo(guildId: String): List<Channel> {
        val url = channelList.replace("{{guild_id}}", guildId)

        val res = OkHttpUtils.getJson(url, officeApiHeader())
        return Gson().fromJson(res.toString(), object : TypeToken<List<Channel>>() {}.type)
    }

    /**
     * 根据message的id获取消息数据
     */
    fun getMessageById(channelId: String, messageId: String): Message {
        val url = getMessage.replace("{{channel_id}}", channelId).replace("{{message_id}}", messageId)

        val res = OkHttpUtils.getJson(url, officeApiHeader())
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    /**
     * 获取指定子频道的权限
     */
    fun getChannelPermissions(channelId: String, userId: String): ChannelPermissions {
        val url = channelPermissions.replace("{{channel_id}}", channelId).replace("{{user_id}}", userId)
        val res = OkHttpUtils.getJson(url, officeApiHeader())
        return Gson().fromJson(res.toString(), ChannelPermissions::class.java)
    }

    /**
     * 修改指定子频道的权限 目前只支持修改查看权限
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
        return res.code == 204
    }
}
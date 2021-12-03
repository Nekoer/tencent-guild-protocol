package com.hcyacg.protocol.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hcyacg.protocol.common.BotApi.replyEmbedNotId
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


    private fun officeApiHeader(token: String): MutableMap<String, String> {
        return mutableMapOf(
            "Authorization" to token
        )
    }


    fun AtMessageCreateEvent.replyText(token: String, msg: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = TextMessage(msg, this.id).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun AtMessageCreateEvent.replyTextNotId(token: String, msg: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeTextMessage(msg).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun AtMessageCreateEvent.replyTextWithImage(token: String, msg: String, imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = TextWithImageMessage(msg, imageUrl, this.id).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun AtMessageCreateEvent.replyTextWithImageNotId(token: String, msg: String, imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeTextWithImageMessage(msg, imageUrl).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun AtMessageCreateEvent.replyImage(token: String, imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = ImageMessage(imageUrl, this.id).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }


    fun AtMessageCreateEvent.replyImageNotId(token: String, imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeImageMessage(imageUrl).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun AtMessageCreateEvent.replyArk(token: String, messageArk: MessageArk): Boolean {
        println(Gson().toJson(messageArk))
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = ArkMessage(messageArk, this.id).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        println(res.toString())
        return true
    }

    fun AtMessageCreateEvent.replyArkNotId(token: String, messageArk: MessageArk): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeArkMessage(messageArk).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun AtMessageCreateEvent.replyEmbed(token: String, messageEmbed: MessageEmbed): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = EmbedMessage(messageEmbed, this.id).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun AtMessageCreateEvent.replyEmbedNotId(token: String, messageEmbed: MessageEmbed): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeEmbedMessage(messageEmbed).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun AtMessageCreateEvent.replyAudio(token: String, audioUrl: String, text: String, status: Int): Boolean {
        val url = sendAudio.replace("{{channel_id}}", this.channel_id)
        val json = AudioControl(audioUrl, text, status).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }


    fun MessageCreateEvent.replyText(token: String, msg: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = TextMessage(msg, this.id).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun MessageCreateEvent.replyTextNotId(token: String, msg: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeTextMessage(msg).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun MessageCreateEvent.replyTextWithImage(token: String, msg: String, imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = TextWithImageMessage(msg, imageUrl, this.id).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun MessageCreateEvent.replyTextWithImageNotId(token: String, msg: String, imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeTextWithImageMessage(msg, imageUrl).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun MessageCreateEvent.replyImage(token: String, imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = ImageMessage(imageUrl, this.id).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }


    fun MessageCreateEvent.replyImageNotId(token: String, imageUrl: String): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeImageMessage(imageUrl).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun MessageCreateEvent.replyArk(token: String, messageArk: MessageArk): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = ArkMessage(messageArk, this.id).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun MessageCreateEvent.replyArkNotId(token: String, messageArk: MessageArk): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeArkMessage(messageArk).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun MessageCreateEvent.replyEmbed(token: String, messageEmbed: MessageEmbed): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = EmbedMessage(messageEmbed, this.id).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    fun MessageCreateEvent.replyEmbedNotId(token: String, messageEmbed: MessageEmbed): Boolean {
        val url = sendMessage.replace("{{channel_id}}", this.channel_id)
        val json = initiativeEmbedMessage(messageEmbed).objectToJson()
        val header = officeApiHeader(token)
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), header)
        return true
    }

    /**
     * 获取频道信息
     */
    fun getGuildById(token: String, guildId: String): Guild {
        val url = guildInfo.replace("{{guild_id}}", guildId)
        val header = officeApiHeader(token)
        val res = OkHttpUtils.getJson(url, header)
        return Gson().fromJson(res.toString(), Guild::class.java)
    }


    /**
     * 获取频道身份组列表
     */
    fun getRolesByGuild(token: String, guildId: String): Roles {
        val url = roles.replace("{{guild_id}}", guildId)
        val header = officeApiHeader(token)
        val res = OkHttpUtils.getJson(url, header)
        return Gson().fromJson(res.toString(), Roles::class.java)
    }

    /**
     * 创建频道身份组
     */
    fun createRolesByGuild(token: String, guildId: String,filter:Filter,info:Info): RoleVo {
        val url = roles.replace("{{guild_id}}", guildId)
        val header = officeApiHeader(token)
        val json = RoleDto(filter, info).objectToJson()
        val res = OkHttpUtils.postJson(url,OkHttpUtils.addJson(json),  header)
        return Gson().fromJson(res.toString(),RoleVo::class.java)
    }

    /**
     * 修改频道身份组
     */
    fun changeRolesByGuild(token: String, guildId: String,roleId:String,filter:Filter,info:Info): RoleVo {
        val url = changeRole.replace("{{guild_id}}", guildId).replace("{{role_id}}",roleId)
        val header = officeApiHeader(token)
        val json = RoleDto(filter, info).objectToJson()
        val res = OkHttpUtils.patchJson(url,OkHttpUtils.addJson(json),header)
        println(res)
        return Gson().fromJson(res.toString(),RoleVo::class.java)
    }

    /**
     * 删除频道身份组
     */
    fun deleteRolesByGuild(token: String, guildId: String,roleId:String): Boolean {
        val url = changeRole.replace("{{guild_id}}", guildId).replace("{{role_id}}",roleId)
        val header = officeApiHeader(token)
        val res = OkHttpUtils.delete(url, mutableMapOf(),header)
        println(res.body?.string())
        return res.code == 204
    }


    /**
     * 增加频道身份组成员(除子频道管理员
     */
    fun createMemberRolesByGuild(token: String, guildId: String,userId:String,roleId:String): Boolean {
        val url = editMemberRole.replace("{{guild_id}}", guildId).replace("{{user_id}}",userId).replace("{{role_id}}",roleId)
        val header = officeApiHeader(token)
        val res = OkHttpUtils.put(url, mutableMapOf(),header)
        return res.code == 204
    }

    /**
     * 增加频道身份组成员(仅子频道管理员
     */
    fun createChildMemberRolesByGuild(token: String, guildId: String,channel: Channel,userId:String,roleId:String): Boolean {
        val url = editMemberRole.replace("{{guild_id}}", guildId).replace("{{user_id}}",userId).replace("{{role_id}}",roleId)
        val json = channel.objectToJson()
        val res = OkHttpUtils.put(url,OkHttpUtils.addJson(json), Headers.headersOf("Authorization",token))
        println(res.body?.string())
        return res.code == 204
    }

    /**
     * 删除频道身份组成员(除子频道管理员
     */
    fun deleteMemberRolesByGuild(token: String, guildId: String,userId:String,roleId:String): Boolean {
        val url = editMemberRole.replace("{{guild_id}}", guildId).replace("{{user_id}}",userId).replace("{{role_id}}",roleId)
        val header = officeApiHeader(token)
        val res = OkHttpUtils.delete(url, mutableMapOf(),header)
        return res.code == 204
    }

    /**
     * 删除频道身份组成员(仅子频道管理员
     */
    fun deleteChildMemberRolesByGuild(token: String, guildId: String,channel: Channel,userId:String,roleId:String): Boolean {
        val url = editMemberRole.replace("{{guild_id}}", guildId).replace("{{user_id}}",userId).replace("{{role_id}}",roleId)
        val header = officeApiHeader(token)
        val json = channel.objectToJson()
        val res = OkHttpUtils.delete(url,OkHttpUtils.addJson(json), header)
        return res.code == 204
    }

    /**
     * 获取单个成员消息
     */
    fun getMemberInfo(token: String, guildId: String,userId:String):Member{
        val url = memberInfo.replace("{{guild_id}}", guildId).replace("{{user_id}}",userId)
        val header = officeApiHeader(token)
        val res = OkHttpUtils.getJson(url,header)
        return Gson().fromJson(res.toString(), Member::class.java)
    }

    /**
     * 获取子频道消息
     */
    fun getChannelInfo(token: String, channelId: String):Channel{
        val url = channel.replace("{{channel_id}}", channelId)
        val header = officeApiHeader(token)
        val res = OkHttpUtils.getJson(url,header)
        return Gson().fromJson(res.toString(), Channel::class.java)
    }

    /**
     * 获取频道下的子频道列表
     */
    fun getChildChannelInfo(token: String, guildId: String):List<Channel>{
        val url = channelList.replace("{{guild_id}}", guildId)
        val header = officeApiHeader(token)
        val res = OkHttpUtils.getJson(url,header)
        return Gson().fromJson(res.toString(), object :TypeToken<List<Channel>>(){}.type)
    }

    /**
     * 根据message的id获取消息数据
     */
    fun getMessageById(token: String, channelId: String,messageId:String):Message{
        val url = getMessage.replace("{{channel_id}}", channelId).replace("{{message_id}}",messageId)
        val header = officeApiHeader(token)
        val res = OkHttpUtils.getJson(url,header)
        return Gson().fromJson(res.toString(), Message::class.java)
    }

    /**
     * 获取指定子频道的权限
     */
    fun getChannelPermissions(token: String, channelId: String,userId:String):ChannelPermissions{
        val url = channelPermissions.replace("{{channel_id}}", channelId).replace("{{user_id}}",userId)
        val header = officeApiHeader(token)
        val res = OkHttpUtils.getJson(url,header)
        return Gson().fromJson(res.toString(), ChannelPermissions::class.java)
    }

    /**
     * 修改指定子频道的权限
     */
    fun changeChannelPermissions(token: String, channelId: String,userId:String,permissions: Permissions,isAdd:Boolean):Boolean{
        val url = channelPermissions.replace("{{channel_id}}", channelId).replace("{{user_id}}",userId)
        val header = officeApiHeader(token)
        val map = mutableMapOf<String,String>()
        if (isAdd){
            map["add"] = 0.shl(1).toString()
        }else{
            map["remove"] = 1.shl(1).toString()
        }
        val res = OkHttpUtils.put(url,map,header)
        return res.code == 204
    }
}
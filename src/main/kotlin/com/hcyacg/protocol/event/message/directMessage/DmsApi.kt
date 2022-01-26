package com.hcyacg.protocol.event.message.directMessage

import com.google.gson.Gson
import com.hcyacg.protocol.constant.Constant
import com.hcyacg.protocol.constant.Constant.Companion.botToken
import com.hcyacg.protocol.constant.Constant.Companion.logger
import com.hcyacg.protocol.constant.Constant.Companion.proUrl
import com.hcyacg.protocol.entity.Message
import com.hcyacg.protocol.event.api.*
import com.hcyacg.protocol.utils.OkHttpUtils

open class DmsApi : DirectMessage() {

    private val sendMessage = "${proUrl}/dms/{{guild_id}}/messages"
    private val dms = "${proUrl}/users/@me/dms"
    private val recall = "${proUrl}/dms/{{guild_id}}/messages/{{message_id}}"

    private fun officeApiHeader(): MutableMap<String, String> {
        return mutableMapOf(
            "Authorization" to Constant.botToken!!
        )
    }

    /**
     * 创建私聊会话
     */
    private fun createDms(authorId:String, guildId:String):Dms{
        val json =  Gson().toJson(DmsDto(authorId,guildId))
        val res = OkHttpUtils.postJson(dms, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Dms::class.java)
    }

    fun replyText(msg: String): Message {
        val url = sendMessage.replace("{{guild_id}}", this.guildId)
        val json = Gson().toJson(TextMessage(msg, this.id))

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }

    fun sendText(authorId:String,guildId:String,msg: String): Message {
        val createDms = createDms(authorId, guildId)
        val url = sendMessage.replace("{{guild_id}}", createDms.guildId)
        val json = Gson().toJson(initiativeTextMessage(msg))

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }

    fun replyTextWithImage(msg: String, imageUrl: String): Message {
        val url = sendMessage.replace("{{guild_id}}", this.guildId)
        val json = Gson().toJson(TextWithImageMessage(msg, imageUrl, this.id))

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }

    fun sendTextWithImage(authorId:String,guildId:String,msg: String, imageUrl: String): Message {
        val createDms = createDms(authorId, guildId)
        val url = sendMessage.replace("{{guild_id}}", createDms.guildId)
        val json = Gson().toJson(initiativeTextWithImageMessage(msg, imageUrl))

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }

    fun replyImage(imageUrl: String): Message {
        val url = sendMessage.replace("{{guild_id}}", this.guildId)
        val json = Gson().toJson(ImageMessage(imageUrl, this.id))
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }


    fun sendImage(authorId:String,guildId:String,imageUrl: String): Message {
        val createDms = createDms(authorId, guildId)
        val url = sendMessage.replace("{{guild_id}}", createDms.guildId)
        val json = Gson().toJson(initiativeImageMessage(imageUrl))
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }

    /**
     * 撤回当前消息
     * 用来撤回频道内的消息
     * 管理员可以撤回普通成员的消息
     * 频道主可以撤回所有人的消息
     */
    fun recall(guildId:String,messageId:String): Boolean {
        val url = recall.replace("{{guild_id}}", guildId).replace("{{message_id}}", messageId)
        val res = OkHttpUtils.delete(url, mutableMapOf(), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 200
    }

}
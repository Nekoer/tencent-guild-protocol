package com.hcyacg.protocol.event

import com.google.gson.Gson
import com.hcyacg.protocol.common.BotApi
import com.hcyacg.protocol.constant.Constant
import com.hcyacg.protocol.constant.Constant.Companion.logger
import com.hcyacg.protocol.constant.Constant.Companion.proUrl
import com.hcyacg.protocol.entity.*
import com.hcyacg.protocol.entity.Message
import com.hcyacg.protocol.event.api.*
import com.hcyacg.protocol.utils.OkHttpUtils

open class EventApi : MessageEvent() {
    private val channel = "${proUrl}/channels/{{channel_id}}"
    private val sendMessage = "$channel/messages"
    private val sendAudio = "$channel/audio"
    private val recall = "$sendMessage/{{message_id}}"

    private val guildInfo = "$proUrl/guilds/{{guild_id}}"
    private val memberMute = "${guildInfo}/members/{{user_id}}/mute"
    private val mute = "$proUrl/guilds/{{guild_id}}/mute"


    private fun officeApiHeader(): MutableMap<String, String> {
        return mutableMapOf(
            "Authorization" to Constant.botToken!!
        )
    }

    fun replyText(msg: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channelId)
        val json = Gson().toJson(TextMessage(msg, this.id))

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }

    fun sendText(msg: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channelId)
        val json = Gson().toJson(initiativeTextMessage(msg))

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }

    fun replyTextWithImage(msg: String, imageUrl: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channelId)
        val json = Gson().toJson(TextWithImageMessage(msg, imageUrl, this.id))

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }

    fun sendTextWithImage(msg: String, imageUrl: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channelId)
        val json = Gson().toJson(initiativeTextWithImageMessage(msg, imageUrl))

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }

    fun replyImage(imageUrl: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channelId)
        val json = Gson().toJson(ImageMessage(imageUrl, this.id))
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }


    fun sendImage(imageUrl: String): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channelId)
        val json = Gson().toJson(initiativeImageMessage(imageUrl))
        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }

    fun replyArk(messageArk: MessageArk): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channelId)
        val json = Gson().toJson(ArkMessage(messageArk, this.id))

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }

    fun sendArk(messageArk: MessageArk): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channelId)
        val json = Gson().toJson(initiativeArkMessage(messageArk))

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }

    fun replyEmbed(messageEmbed: MessageEmbed): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channelId)
        val json = Gson().toJson(EmbedMessage(messageEmbed, this.id))

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }

    fun sendEmbed(messageEmbed: MessageEmbed): Message {
        val url = sendMessage.replace("{{channel_id}}", this.channelId)
        val json = Gson().toJson(initiativeEmbedMessage(messageEmbed))

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }

    fun sendAudio(audioUrl: String, text: String, status: Int): Message {
        val url = sendAudio.replace("{{channel_id}}", this.channelId)
        val json = Gson().toJson(AudioControl(audioUrl, text, status))

        val res = OkHttpUtils.postJson(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res)
        return Gson().fromJson(res, Message::class.java)
    }


    /**
     * 全局禁言
     * @param timestamp 时间戳 单位：秒
     */
    fun mute(timestamp: Long): Boolean {
        val url = mute.replace("{{guild_id}}", guildId)
        val json = "{\"mute_end_timestamp\": ${timestamp}}"
        val res = OkHttpUtils.patch(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 204
    }

    /**
     * 全局禁言
     * @param seconds 时间戳 单位：秒
     */
    fun mute(seconds: Int): Boolean {
        val url = mute.replace("{{guild_id}}", guildId)
        val json = "{\"mute_seconds\": ${seconds}}"
        val res = OkHttpUtils.patch(url, OkHttpUtils.addJson(json), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 204
    }


    /**
     * 撤回当前消息
     * 用来撤回频道内的消息
     * 管理员可以撤回普通成员的消息
     * 频道主可以撤回所有人的消息
     */
    fun recall(): Boolean {
        val url = recall.replace("{{channel_id}}", channelId).replace("{{message_id}}", id)
        val res = OkHttpUtils.delete(url, mutableMapOf(), officeApiHeader())
        logger.debug(res.code.toString())
        return res.code == 200
    }

}
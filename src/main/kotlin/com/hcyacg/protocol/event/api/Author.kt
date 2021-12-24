package com.hcyacg.protocol.event.api

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import com.hcyacg.protocol.constant.Constant
import com.hcyacg.protocol.constant.Constant.Companion.proUrl
import com.hcyacg.protocol.constant.Constant.Companion.threadLocal
import com.hcyacg.protocol.utils.OkHttpUtils

@NoArg
open class Author{
    @SerializedName("avatar")
    lateinit var avatar: String
    @SerializedName("bot")
    var bot: Boolean = false
    @SerializedName("id")
    lateinit var id: String
    @SerializedName("username")
    lateinit var username: String

    private val guildInfo = "${proUrl}/guilds/{{guild_id}}"
    private val memberMute = "${guildInfo}/members/{{user_id}}/mute"

    private fun officeApiHeader(): MutableMap<String, String> {
        return mutableMapOf(
            "Authorization" to Constant.botToken!!
        )
    }

    /**
     * 成员禁言
     * @param timestamp 时间戳 单位：秒
     */
    fun mute(timestamp: Long): Boolean {
        val url = memberMute.replace("{{guild_id}}", threadLocal.get()).replace("{{user_id}}", this.id)
        val json = "{\"mute_end_timestamp\": ${timestamp}}"
        val res = OkHttpUtils.patch(url, OkHttpUtils.addJson(json), officeApiHeader())
        Constant.logger.debug(res.code.toString())
        return res.code == 204
    }

    /**
     * 成员禁言
     * @param seconds 时间戳 单位：秒
     */
    fun mute(seconds: Int): Boolean {
        val guildId = threadLocal.get()
        val url = memberMute.replace("{{guild_id}}", guildId).replace("{{user_id}}", this.id)
        val json = "{\"mute_seconds\": ${seconds}}"
        val res = OkHttpUtils.patch(url, OkHttpUtils.addJson(json), officeApiHeader())
        Constant.logger.debug(res.code.toString())
        return res.code == 204
    }
}
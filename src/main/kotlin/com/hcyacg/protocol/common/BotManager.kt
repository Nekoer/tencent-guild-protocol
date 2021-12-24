package com.hcyacg.protocol.common

import com.hcyacg.protocol.constant.Constant.Companion.botToken
import com.hcyacg.protocol.internal.config.IdentifyConfig
import com.hcyacg.protocol.internal.config.Intents
import kotlinx.coroutines.runBlocking

object BotManager {

    private var isPrivate: Boolean = false
    private lateinit var token: String


    /**
     * 默认公域
     */
    private var intent: Intents = Intents(
        guilds = true,
        guildMembers = true,
        directMessage = false,
        audioAction = true,
        atMessages = true,
        messages = false,
        forum = false,
        guildMessageReactions = false
    )

    @JvmStatic
    fun configuration(token:String, isPrivate:Boolean):BotManager{
        this.token = token
        this.isPrivate = isPrivate

        botToken = token

        /**
         * 判断是否是私域
         */
        if (BotManager.isPrivate) {
            intent = Intents(
                guilds = true,
                guildMembers = true,
                directMessage = false,
                audioAction = true,
                atMessages = true,
                messages = true,
                forum = false,
                guildMessageReactions = true
            )
        }
        return this
    }


    @JvmStatic
    fun addListen(vararg listener: BotEvent) {
        val list = listener.toMutableList()
        list.add(MonitorMessage())
        val gatewayAccessWithFragmentedWss = Gateway.gatewayAccessWithFragmentedWss(botToken!!)

        for (i in 0 until gatewayAccessWithFragmentedWss!!.shards) {
            runBlocking {
                BotClient(IdentifyConfig(botToken!!, gatewayAccessWithFragmentedWss.shards, i, intent), list)
            }
        }

    }
}
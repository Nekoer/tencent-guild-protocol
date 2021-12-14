package com.hcyacg.protocol.common

import com.hcyacg.protocol.constant.Constant.Companion.botToken
import com.hcyacg.protocol.internal.config.IdentifyConfig
import com.hcyacg.protocol.internal.config.Intents
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class BotManager(token: String,isPrivate:Boolean) {

    /**
     * 默认公域
     */
    var intent :Intents = Intents(guilds = true, guildMembers = true,directMessage = false,audioAction = true,atMessages = true,messages = false)
    init {
        if (null == botToken) {
            botToken = token
        }

        /**
         * 判断是否是私域
         */
        if (isPrivate){
            intent = Intents(guilds = true, guildMembers = true,directMessage = false,audioAction = true,atMessages = true,messages = true)
        }
    }

    fun addListen(vararg listener: BotEvent) {
        val list = listener.toMutableList()
        list.add(MonitorMessage())
        val gatewayAccessWithFragmentedWss = Gateway.gatewayAccessWithFragmentedWss(botToken!!)

        runBlocking {
            for (i in 0 until gatewayAccessWithFragmentedWss!!.shards){
                BotClient(IdentifyConfig(botToken!!, gatewayAccessWithFragmentedWss.shards, i,intent), list)
                delay(3000L)
            }
        }
    }
}
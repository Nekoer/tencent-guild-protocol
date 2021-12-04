package com.hcyacg.protocol.common

import com.hcyacg.protocol.constant.Constant.Companion.botToken
import com.hcyacg.protocol.internal.config.IdentifyConfig
import com.hcyacg.protocol.internal.config.Intents
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class BotManager(token: String,intents:Intents) {

    var intent = intents


    init {
        if (null == botToken) {
            botToken = token
        }
    }

    fun addListen(listener: List<BotEvent>) {
        val list = listener.toMutableList()
        list.add(MonitorMessage())
        runBlocking {
            BotClient(IdentifyConfig(botToken!!, 4, 0,intent), list)
            delay(3000L)
            BotClient(IdentifyConfig(botToken!!, 4, 1,intent), list)
            delay(3000L)
            BotClient(IdentifyConfig(botToken!!, 4, 2,intent), list)
            delay(3000L)
            BotClient(IdentifyConfig(botToken!!, 4, 3,intent), list)
        }
    }

}
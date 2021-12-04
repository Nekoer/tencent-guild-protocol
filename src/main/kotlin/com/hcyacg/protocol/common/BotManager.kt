package com.hcyacg.protocol.common

import com.hcyacg.protocol.constant.Constant.Companion.botToken
import com.hcyacg.protocol.internal.config.IdentifyConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class BotManager(token: String) {

    init {
        if (null == botToken) {
            botToken = token
        }
    }

    fun addListen(listener: List<BotEvent>) {
        val list = listener.toMutableList()
        list.add(MonitorMessage())
        runBlocking {
            BotClient(IdentifyConfig(botToken!!, 4, 0), list)
            delay(3000L)
            BotClient(IdentifyConfig(botToken!!, 4, 1), list)
            delay(3000L)
            BotClient(IdentifyConfig(botToken!!, 4, 2), list)
            delay(3000L)
            BotClient(IdentifyConfig(botToken!!, 4, 3), list)
        }
    }

}
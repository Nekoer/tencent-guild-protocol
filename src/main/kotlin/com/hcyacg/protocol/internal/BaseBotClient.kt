package com.hcyacg.protocol.internal

/**
 * 机器人客户端父类
 */
internal interface BaseBotClient {

    fun reconnect()

    fun sendMessage(text: String)
}
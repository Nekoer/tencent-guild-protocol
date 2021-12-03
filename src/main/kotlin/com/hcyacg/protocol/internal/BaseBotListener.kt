package com.hcyacg.protocol.internal

import okhttp3.WebSocketListener

/**
 * 机器人监听父类
 */
abstract class BaseBotListener : WebSocketListener() {

    lateinit var reconnect :()->Unit

    fun reconnect(func:()->Unit){
        reconnect = func
    }
}
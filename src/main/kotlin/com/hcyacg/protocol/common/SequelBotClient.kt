package com.hcyacg.protocol.common

import com.hcyacg.protocol.constant.Constant.Companion.logger
import com.hcyacg.protocol.internal.BaseBotClient
import com.hcyacg.protocol.internal.BaseBotListener
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

/**
 * 中续 机器人客户端
 */
open class SequelBotClient<T: BaseBotListener> (
    private val uri: String,
    private val listener: T
): BaseBotClient {
    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS) //设置读取超时时间
            .writeTimeout(30, TimeUnit.SECONDS) //设置写的超时时间
            .connectTimeout(30, TimeUnit.SECONDS) //设置连接超时时间
            .build()
    }
    private val request: Request by lazy { Request.Builder().get().url(uri).build() }

    private var connectWebSocket = client.newWebSocket(request, listener)

    init {
        listener.reconnect { reconnect() }
    }

    override fun reconnect(){
        connectWebSocket.close(1000,"reconnect")
        logger.info("重连中 ... ")
        connectWebSocket = client.newWebSocket(request,listener)
    }

    override fun sendMessage(text: String){
        logger.info("发送信息 $text")
        connectWebSocket.send(text)
    }

}
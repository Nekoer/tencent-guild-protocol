package com.hcyacg.protocol.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hcyacg.protocol.event.*
import com.hcyacg.protocol.internal.BaseBotListener
import com.hcyacg.protocol.internal.config.IdentifyConfig
import com.hcyacg.protocol.internal.entity.*
import com.hcyacg.protocol.internal.enums.DispatchEnums
import com.hcyacg.protocol.internal.enums.OPCodeEnums
import com.hcyacg.protocol.internal.enums.OPCodeEnums.*
import com.hcyacg.protocol.utils.JsonUtils.jsonToObjectOrNull
import com.hcyacg.protocol.utils.JsonUtils.objectToJson
import com.hcyacg.protocol.utils.ScheduleUtils
import kotlinx.coroutines.runBlocking
import okhttp3.Response
import okhttp3.WebSocket
import okio.ByteString
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.wsure.guild.bot.official.dtos.event.ReadyEvent
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicLong

class BotListener(
    private val config: IdentifyConfig,
    private val officialEvents: List<BotEvent> = emptyList(),
    private val heartbeatDelay: Long = 30000,
    private val reconnectTimeout: Long = 60000,
) : BaseBotListener() {
    private val identifyOpDto = Identify(config.toIdentifyOperationData()).objectToJson()
    var sessionId: String = ""

    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val logHeader = "${config.index + 1} of ${config.shards}"

    private var hbTimer: Timer? = null
    private val messageSeq by lazy { AtomicLong(0) }
    private val lastReceivedHeartBeat = AtomicLong(0)
    private val isResume = AtomicBoolean(false)


    override fun onOpen(webSocket: WebSocket, response: Response) {
        logger.info("$logHeader 正在连接中 ")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        runCatching {
            logger.trace("$logHeader 收到了信息 $text")

            text.jsonToObjectOrNull<Operation>()?.also { opType ->

                when (opType.op) {
                    HEARTBEAT_ACK -> {
                        lastReceivedHeartBeat.getAndSet(System.currentTimeMillis())
                    }
                    //首次连接 发送Identify信息鉴权
                    HELLO -> {
                        // 初始化操作
                        initConnection(webSocket)
                    }
                    //收到事件
                    DISPATCH -> {
                        text.jsonToObjectOrNull<DispatchType>()?.also { dispatchDto ->
                            successConnect(dispatchDto)
//                            logger.debug("$logHeader 收到了事件:${dispatchDto.type} 内容:$text")
                            when (dispatchDto.type) {
                                DispatchEnums.READY -> {
                                    text.jsonToObjectOrNull<Dispatch<ReadyEvent>>()?.also { readyEvent ->
                                        sessionId = readyEvent.d.session_id
                                        officialEvents.forEach {
                                            runBlocking {
                                                it.onReady(readyEvent.d)
                                            }
                                        }
                                    }
                                }
                                DispatchEnums.GUILD_MEMBER_ADD -> {
                                    text.jsonToObjectOrNull<Dispatch<GuildMemberEvent>>()?.also { guildMemberEvent ->
                                        officialEvents.forEach {
                                            runBlocking {
                                                it.onGuildMemberAdd(guildMemberEvent.d)
                                            }
                                        }
                                    }
                                }
                                DispatchEnums.GUILD_MEMBER_UPDATE -> {
                                    text.jsonToObjectOrNull<Dispatch<GuildMemberEvent>>()?.also { guildMemberEvent ->
                                        officialEvents.forEach {
                                            runBlocking {
                                                it.onGuildMemberUpdate(guildMemberEvent.d)
                                            }
                                        }
                                    }
                                }
                                DispatchEnums.GUILD_MEMBER_REMOVE -> {
                                    text.jsonToObjectOrNull<Dispatch<GuildMemberEvent>>()?.also { guildMemberEvent ->
                                        officialEvents.forEach {
                                            runBlocking {
                                                it.onGuildMemberRemove(guildMemberEvent.d)
                                            }
                                        }
                                    }
                                }
                                DispatchEnums.AT_MESSAGE_CREATE -> {
                                    text.jsonToObjectOrNull<Dispatch<AtMessageCreateEvent>>()?.also { guildAtMessage ->
                                        officialEvents.forEach { runBlocking { it.onAtMessageCreate(guildAtMessage.d) } }
                                    }
                                }
                                DispatchEnums.MESSAGE_CREATE -> {
                                    text.jsonToObjectOrNull<Dispatch<MessageCreateEvent>>()?.also { guildAtMessage ->
                                        officialEvents.forEach { runBlocking { it.onMessageCreate(guildAtMessage.d) } }
                                    }
                                }
                                DispatchEnums.CHANNEL_CREATE -> {
                                    text.jsonToObjectOrNull<Dispatch<ChannelEvent>>()?.also { channelEvent ->
                                        officialEvents.forEach { runBlocking { it.onChannelCreate(channelEvent.d) } }
                                    }
                                }
                                DispatchEnums.CHANNEL_UPDATE -> {
                                    text.jsonToObjectOrNull<Dispatch<ChannelEvent>>()?.also { channelEvent ->
                                        officialEvents.forEach { runBlocking { it.onChannelUpdate(channelEvent.d) } }
                                    }
                                }
                                DispatchEnums.CHANNEL_DELETE -> {
                                    text.jsonToObjectOrNull<Dispatch<ChannelEvent>>()?.also { channelEvent ->
                                        officialEvents.forEach { runBlocking { it.onChannelDelete(channelEvent.d) } }
                                    }
                                }
                                DispatchEnums.GUILD_CREATE -> {
                                    text.jsonToObjectOrNull<Dispatch<GuildEvent>>()?.also { guildEvent ->
                                        officialEvents.forEach { runBlocking { it.onGuildCreate(guildEvent.d) } }
                                    }
                                }
                                DispatchEnums.GUILD_UPDATE -> {
                                    text.jsonToObjectOrNull<Dispatch<GuildEvent>>()?.also { guildEvent ->
                                        officialEvents.forEach { runBlocking { it.onGuildUpdate(guildEvent.d) } }
                                    }
                                }
                                DispatchEnums.GUILD_DELETE -> {
                                    text.jsonToObjectOrNull<Dispatch<GuildEvent>>()?.also { guildEvent ->
                                        officialEvents.forEach { runBlocking { it.onGuildDelete(guildEvent.d) } }
                                    }
                                }
                                DispatchEnums.RESUMED -> {
                                    officialEvents.forEach { runBlocking { it.onResumed(config, sessionId) } }
                                }
                                else -> {
                                    logger.warn("$logHeader 未知的事件! 信息:$text")
                                }
                            }
                        }
                    }
                    RECONNECT -> {
                        logger.warn("$logHeader 需要重连!")
                        isResume.getAndSet(true)
                        reconnectClient()
                    }
                    INVALID_SESSION -> {
                        logger.error("$logHeader 错误:", INVALID_SESSION.description)
                        failureConnect(webSocket)

                    }
                    HEARTBEAT -> TODO()
                    IDENTIFY -> TODO()
                    RESUME -> TODO()
                    UNKNOWN -> TODO()
                }
            }
        }.onFailure {
            it.printStackTrace()
        }
    }

    private fun failureConnect(webSocket: WebSocket) {
        if (isResume.get()) {
            isResume.set(false)
            reconnectClient()
        } else {
            webSocket.cancel()
            hbTimer?.cancel()
            throw RuntimeException(INVALID_SESSION.description)
        }
    }

    private fun successConnect(dispatchDto: DispatchType) {
        messageSeq.getAndSet(dispatchDto.seq)
        isResume.set(false)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        logger.warn("$logHeader 正在关闭")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        logger.warn("$logHeader 已关闭")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        logger.warn("$logHeader 尝试重新连接")
        reconnectClient()
    }

    private fun initConnection(webSocket: WebSocket) {
        // 鉴权
        if (isResume.get()) {
            val resume = Resume(ResumeData(messageSeq.get(), sessionId, config.token))
            webSocket.sendAndPrintLog(resume.objectToJson())
        } else {
            webSocket.sendAndPrintLog(identifyOpDto)
        }
        // 启动心跳发送
        lastReceivedHeartBeat.getAndSet(System.currentTimeMillis())
        val processor = createHeartBeatProcessor(webSocket)
        //  先取消以前的定时器
        hbTimer?.cancel()
        // 启动新的心跳
        hbTimer = ScheduleUtils.loopEvent(processor, Date(), heartbeatDelay)
    }

    private fun createHeartBeatProcessor(webSocket: WebSocket): suspend () -> Unit {
        return suspend {
            val last = lastReceivedHeartBeat.get()
            val now = System.currentTimeMillis()
            if (now - last > reconnectTimeout) {
                logger.warn("$logHeader 心跳超时，尝试重新连接")
                reconnectClient()
            } else {
                val hb = Heartbeat(messageSeq.get()).objectToJson()
                webSocket.sendAndPrintLog(hb, true)
            }
        }
    }

    private fun reconnectClient() {
        hbTimer?.cancel()
        reconnect()
    }

    private fun WebSocket.sendAndPrintLog(text: String, isHeartbeat: Boolean = false) {
//        if (isHeartbeat) {
//            logger.debug("$logHeader 发送心跳包 $text")
//        } else {
//            logger.info("$logHeader 发送信息 $text")
//        }
        this.send(text)
    }

}
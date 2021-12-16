package com.hcyacg.protocol.common

import com.google.gson.Gson
import com.hcyacg.protocol.constant.Constant.Companion.botToken
import com.hcyacg.protocol.constant.Constant.Companion.logger
import com.hcyacg.protocol.event.*
import com.hcyacg.protocol.internal.config.IdentifyConfig
import com.hcyacg.protocol.event.ReadyEvent

/**
 * 频道事件
 */
abstract class BotEvent {


    open fun getToken():String?{
        return botToken
    }

    open suspend fun onReady(data: ReadyEvent) {}

    open suspend fun onGuildMemberAdd(data: GuildMemberEvent) {}

    open suspend fun onGuildMemberUpdate(data: GuildMemberEvent) {}

    open suspend fun onGuildMemberRemove(data: GuildMemberEvent) {}

    open suspend fun onAtMessageCreate(data: AtMessageCreateEvent) {}

    open suspend fun onMessageCreate(data: MessageCreateEvent) {}

    open suspend fun onChannelCreate(data: ChannelEvent) {}

    open suspend fun onChannelUpdate(data: ChannelEvent) {}

    open suspend fun onChannelDelete(data: ChannelEvent) {}

    open suspend fun onGuildCreate(data: GuildEvent) {}

    open suspend fun onGuildUpdate(data: GuildEvent) {}

    open suspend fun onGuildDelete(data: GuildEvent) {}

    open suspend fun onResumed(config: IdentifyConfig, sessionId: String) {}

    open suspend fun onMessageReactionAdd(data:MessageReactionEvent){}

    open suspend fun onMessageReactionRemove(data:MessageReactionEvent){}

    open suspend fun onAudioStart(data:AudioActionEvent){}

    open suspend fun onAudioFinish(data:AudioActionEvent){}

    open suspend fun onAudioOnMic(data:AudioActionEvent){}

    open suspend fun onAudioOffMic(data:AudioActionEvent){}

    //TODO 官方有许多事件，后续在这里添加事件名称

}
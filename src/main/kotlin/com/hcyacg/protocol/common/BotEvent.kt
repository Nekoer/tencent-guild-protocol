package com.hcyacg.protocol.common

import com.hcyacg.protocol.constant.Constant.Companion.botToken
import com.hcyacg.protocol.event.*
import com.hcyacg.protocol.internal.config.IdentifyConfig
import com.hcyacg.protocol.event.ReadyEvent
import com.hcyacg.protocol.event.message.*
import com.hcyacg.protocol.event.message.directMessage.DirectMessageCreateEvent

/**
 * 频道事件
 */
abstract class BotEvent {


    open fun getToken(): String? {
        return botToken
    }

    open suspend fun onReady(event: ReadyEvent) {}

    open suspend fun onGuildMemberAdd(event: GuildMemberEvent) {}

    open suspend fun onGuildMemberUpdate(event: GuildMemberEvent) {}

    open suspend fun onGuildMemberRemove(event: GuildMemberEvent) {}

    open suspend fun onAtMessageCreate(event: AtMessageCreateEvent) {}

    open suspend fun onMessageCreate(event: MessageCreateEvent) {}

    open suspend fun onDirectMessageCreate(event: DirectMessageCreateEvent) {}

    open suspend fun onChannelCreate(event: ChannelEvent) {}

    open suspend fun onChannelUpdate(event: ChannelEvent) {}

    open suspend fun onChannelDelete(event: ChannelEvent) {}

    open suspend fun onGuildCreate(event: GuildEvent) {}

    open suspend fun onGuildUpdate(event: GuildEvent) {}

    open suspend fun onGuildDelete(event: GuildEvent) {}

    open suspend fun onResumed(config: IdentifyConfig, sessionId: String) {}

    open suspend fun onMessageReactionAdd(event: MessageReactionEvent) {}

    open suspend fun onMessageReactionRemove(event: MessageReactionEvent) {}

    open suspend fun onMessageAuditPass(event: MessageAuditPassEvent) {}

    open suspend fun onMessageAuditReject(event: MessageAuditRejectEvent) {}

    open suspend fun onAudioStart(event: AudioActionEvent) {}

    open suspend fun onAudioFinish(event: AudioActionEvent) {}

    open suspend fun onAudioOnMic(event: AudioActionEvent) {}

    open suspend fun onAudioOffMic(event: AudioActionEvent) {}

    //TODO 官方有许多事件，后续在这里添加事件名称

}
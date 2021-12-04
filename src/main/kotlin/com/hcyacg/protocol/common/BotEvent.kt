package com.hcyacg.protocol.common

import com.google.gson.Gson
import com.hcyacg.protocol.constant.Constant.Companion.botToken
import com.hcyacg.protocol.constant.Constant.Companion.logger
import com.hcyacg.protocol.event.*
import com.hcyacg.protocol.internal.config.IdentifyConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.hcyacg.protocol.event.ReadyEvent

/**
 * 频道事件
 */
abstract class BotEvent {


    open fun getToken():String?{
        return botToken
    }

    open suspend fun onReady(data: ReadyEvent) {
        logger.info("${data.shard[0] + 1} of ${data.shard[1]} 已连接")
    }

    open suspend fun onGuildMemberAdd(data: GuildMemberEvent) {
        logger.info("${data.user.username}(${data.user.id}) 加入了 ${BotApi.getGuildById(data.guild_id).name}(${data.guild_id})")
    }

    open suspend fun onGuildMemberUpdate(data: GuildMemberEvent) {}

    open suspend fun onGuildMemberRemove(data: GuildMemberEvent) {
        logger.info("${data.user.username}(${data.user.id}) 退出了 ${BotApi.getGuildById(data.guild_id).name}(${data.guild_id})")
    }

    open suspend fun onAtMessageCreate(data: AtMessageCreateEvent) {
        logger.info("${BotApi.getGuildById(data.guild_id).name}(${data.guild_id}) - ${BotApi.getChannelInfo(data.channel_id).name}(${data.channel_id}) - ${data.author.username}(${data.author.id}): ${data.content}")
    }

    open suspend fun onMessageCreate(data: MessageCreateEvent) {
        logger.info("${BotApi.getGuildById(data.guild_id).name}(${data.guild_id}) - ${BotApi.getChannelInfo(data.channel_id).name}(${data.channel_id}) - ${data.author.username}(${data.author.id}):${if(data.attachments.isNotEmpty())  Gson().toJson(data.attachments) else ""} ${data.content}")
    }

    open suspend fun onChannelCreate(data: ChannelEvent) {
        logger.info("${BotApi.getGuildById(data.guild_id).name}(${data.guild_id}) - ${data.name}(${data.id}) 子频道被创建")
    }

    open suspend fun onChannelUpdate(data: ChannelEvent) {
        logger.info("${BotApi.getGuildById(data.guild_id).name}(${data.guild_id}) - ${data.name}(${data.id}) 子频道信息已被修改")
    }

    open suspend fun onChannelDelete(data: ChannelEvent) {
        logger.info("${BotApi.getGuildById(data.guild_id).name}(${data.guild_id}) - ${data.name}(${data.id}) 子频道被删除")
    }

    open suspend fun onGuildCreate(data: GuildEvent) {
        logger.info("机器人加入了 ${data.name}(${data.id}) 介绍: ${data.description}")
    }

    open suspend fun onGuildUpdate(data: GuildEvent) {
        logger.info("${data.name}(${data.id}) 频道信息变更")

    }

    open suspend fun onGuildDelete(data: GuildEvent) {
        logger.info("机器人离开了 ${data.name}(${data.id})")
    }

    open suspend fun onResumed(config: IdentifyConfig, sessionId: String) {}


    //TODO 官方有许多事件，后续在这里添加事件名称

}
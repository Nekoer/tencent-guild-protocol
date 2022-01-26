package com.hcyacg.protocol.common

import com.google.gson.Gson
import com.hcyacg.protocol.constant.Constant
import com.hcyacg.protocol.constant.Constant.Companion.bot
import com.hcyacg.protocol.constant.Constant.Companion.logger
import com.hcyacg.protocol.event.*
import com.hcyacg.protocol.event.message.*
import com.hcyacg.protocol.event.message.directMessage.DirectMessageCreateEvent


internal class MonitorMessage : BotEvent() {

    override suspend fun onReady(event: ReadyEvent) {
        bot = event.user
        logger.info("${event.shard[0] + 1} of ${event.shard[1]} 已连接")
    }

    override suspend fun onGuildMemberAdd(event: GuildMemberEvent) {
        logger.info("${event.user.username}(${event.user.id}) 加入了 ${BotApi.getGuildById(event.guildId).name}(${event.guildId})")
    }

    override suspend fun onGuildMemberUpdate(event: GuildMemberEvent) {}

    override suspend fun onGuildMemberRemove(event: GuildMemberEvent) {
        logger.info("${event.user.username}(${event.user.id}) 退出了 ${BotApi.getGuildById(event.guildId).name}(${event.guildId})")
    }

    override suspend fun onAtMessageCreate(event: AtMessageCreateEvent) {
        logger.info(
            "${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${
                BotApi.getChannelInfo(
                    event.channelId
                ).name
            }(${event.channelId}) - ${event.author.username}(${event.author.id}): ${event.content}"
        )
    }

    override suspend fun onMessageCreate(event: MessageCreateEvent) {
        if (event.isContentInitialized() && event.content.indexOf("<@!${bot!!.id}>") < 0) {
            logger.info(
                "${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${
                    BotApi.getChannelInfo(
                        event.channelId
                    ).name
                }(${event.channelId}) - ${event.author.username}(${event.author.id}):${
                    if (null != event.attachments && event.attachments.isNotEmpty()) Gson().toJson(
                        event.attachments
                    ) else ""
                } ${event.content}"
            )
        }
    }

    override suspend fun onDirectMessageCreate(event: DirectMessageCreateEvent) {
        if (event.isContentInitialized() && event.content.indexOf("<@!${bot!!.id}>") < 0) {
            logger.info(
                "${event.author.username}(${event.author.id}): ${event.content}"
            )
        }
    }

    override suspend fun onChannelCreate(event: ChannelEvent) {
        logger.info("${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${event.name}(${event.id}) 子频道被创建")
    }

    override suspend fun onChannelUpdate(event: ChannelEvent) {
        logger.info("${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${event.name}(${event.id}) 子频道信息已被修改")
    }

    override suspend fun onChannelDelete(event: ChannelEvent) {
        logger.info("${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${event.name}(${event.id}) 子频道被删除")
    }

    override suspend fun onGuildCreate(event: GuildEvent) {
        logger.info("机器人加入了 ${event.name}(${event.id}) 介绍: ${event.description}")
    }

    override suspend fun onGuildUpdate(event: GuildEvent) {
        logger.info("${event.name}(${event.id}) 频道信息变更")

    }

    override suspend fun onGuildDelete(event: GuildEvent) {
        logger.info("机器人离开了 ${event.name}(${event.id})")
    }

    override suspend fun onMessageReactionAdd(event: MessageReactionEvent) {
        val memberInfo = BotApi.getMemberInfo(event.guildId, event.userId)
       logger.info(
            "${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${
                BotApi.getChannelInfo(
                    event.channelId
                ).name
            }(${event.channelId}) - ${memberInfo.user!!.username}(${event.userId}): 添加了表情表态对象: ${event.emoji} ${
                getInfo(
                    event.emoji.id
                )
            }"
        )
    }

    override suspend fun onMessageReactionRemove(event: MessageReactionEvent) {
        val memberInfo = BotApi.getMemberInfo(event.guildId, event.userId)
        logger.info(
            "${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${
                BotApi.getChannelInfo(
                    event.channelId
                ).name
            }(${event.channelId}) - ${memberInfo.user!!.username}(${event.userId}): 删除了表情表态对象: ${event.emoji} ${
                getInfo(
                    event.emoji.id
                )
            }"
        )
    }


    override suspend fun onAudioStart(event: AudioActionEvent) {
        logger.info(
            "${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${
                BotApi.getChannelInfo(
                    event.channelId
                ).name
            }(${event.channelId}) - ${event.text} -${event.audioUrl}"
        )
    }

    override suspend fun onAudioFinish(event: AudioActionEvent) {
        logger.info(
            "${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${
                BotApi.getChannelInfo(
                    event.channelId
                ).name
            }(${event.channelId}) - ${event.text} -${event.audioUrl}"
        )
    }

    override suspend fun onAudioOnMic(event: AudioActionEvent) {
        logger.info(
            "${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${
                BotApi.getChannelInfo(
                    event.channelId
                ).name
            }(${event.channelId}) - 上麦了"
        )

    }

    override suspend fun onAudioOffMic(event: AudioActionEvent) {
        logger.info(
            "${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${
                BotApi.getChannelInfo(
                    event.channelId
                ).name
            }(${event.channelId}) - 下麦了"
        )

    }

    override suspend fun onMessageAuditPass(event: MessageAuditPassEvent) {
        logger.info("${event.createTime} 发送的消息未通过审核")
    }

    override suspend fun onMessageAuditReject(event: MessageAuditRejectEvent) {
        logger.info("${event.createTime} 发送的消息通过审核")
    }
}

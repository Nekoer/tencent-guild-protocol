package com.hcyacg.protocol.common

import com.google.gson.Gson
import com.hcyacg.protocol.constant.Constant
import com.hcyacg.protocol.constant.Constant.Companion.bot
import com.hcyacg.protocol.event.*


internal class MonitorMessage : BotEvent() {

    override suspend fun onReady(event: ReadyEvent) {
        bot = event.user
        Constant.logger.info("${event.shard[0] + 1} of ${event.shard[1]} 已连接")
    }

    override suspend fun onGuildMemberAdd(event: GuildMemberEvent) {
        Constant.logger.info("${event.user.username}(${event.user.id}) 加入了 ${BotApi.getGuildById(event.guildId).name}(${event.guildId})")
    }

    override suspend fun onGuildMemberUpdate(event: GuildMemberEvent) {}

    override suspend fun onGuildMemberRemove(event: GuildMemberEvent) {
        Constant.logger.info("${event.user.username}(${event.user.id}) 退出了 ${BotApi.getGuildById(event.guildId).name}(${event.guildId})")
    }

    override suspend fun onAtMessageCreate(event: AtMessageCreateEvent) {
        Constant.logger.info(
            "${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${
                BotApi.getChannelInfo(
                    event.channelId
                ).name
            }(${event.channelId}) - ${event.author.username}(${event.author.id}): ${event.content}"
        )
    }

    override suspend fun onMessageCreate(event: MessageCreateEvent) {
        if (event.content.indexOf("<@!${bot!!.id}>") < 0) {
            Constant.logger.info(
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

    override suspend fun onChannelCreate(event: ChannelEvent) {
        Constant.logger.info("${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${event.name}(${event.id}) 子频道被创建")
    }

    override suspend fun onChannelUpdate(event: ChannelEvent) {
        Constant.logger.info("${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${event.name}(${event.id}) 子频道信息已被修改")
    }

    override suspend fun onChannelDelete(event: ChannelEvent) {
        Constant.logger.info("${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${event.name}(${event.id}) 子频道被删除")
    }

    override suspend fun onGuildCreate(event: GuildEvent) {
        Constant.logger.info("机器人加入了 ${event.name}(${event.id}) 介绍: ${event.description}")
    }

    override suspend fun onGuildUpdate(event: GuildEvent) {
        Constant.logger.info("${event.name}(${event.id}) 频道信息变更")

    }

    override suspend fun onGuildDelete(event: GuildEvent) {
        Constant.logger.info("机器人离开了 ${event.name}(${event.id})")
    }

    override suspend fun onMessageReactionAdd(event: MessageReactionEvent) {
        val memberInfo = BotApi.getMemberInfo(event.guildId, event.userId)
        Constant.logger.info(
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
        Constant.logger.info(
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
        Constant.logger.info(
            "${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${
                BotApi.getChannelInfo(
                    event.channelId
                ).name
            }(${event.channelId}) - ${event.text} -${event.audioUrl}"
        )
    }

    override suspend fun onAudioFinish(event: AudioActionEvent) {
        Constant.logger.info(
            "${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${
                BotApi.getChannelInfo(
                    event.channelId
                ).name
            }(${event.channelId}) - ${event.text} -${event.audioUrl}"
        )
    }

    override suspend fun onAudioOnMic(event: AudioActionEvent) {
        Constant.logger.info(
            "${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${
                BotApi.getChannelInfo(
                    event.channelId
                ).name
            }(${event.channelId}) - 上麦了"
        )

    }

    override suspend fun onAudioOffMic(event: AudioActionEvent) {
        Constant.logger.info(
            "${BotApi.getGuildById(event.guildId).name}(${event.guildId}) - ${
                BotApi.getChannelInfo(
                    event.channelId
                ).name
            }(${event.channelId}) - 下麦了"
        )

    }
}

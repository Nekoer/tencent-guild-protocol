package com.hcyacg.protocol.common

import com.google.gson.Gson
import com.hcyacg.protocol.constant.Constant
import com.hcyacg.protocol.constant.Constant.Companion.bot
import com.hcyacg.protocol.event.*


internal class MonitorMessage : BotEvent() {

    override suspend fun onReady(data: ReadyEvent) {
        bot = data.user
        Constant.logger.info("${data.shard[0] + 1} of ${data.shard[1]} 已连接")
    }

    override suspend fun onGuildMemberAdd(data: GuildMemberEvent) {
        Constant.logger.info("${data.user.username}(${data.user.id}) 加入了 ${BotApi.getGuildById(data.guildId).name}(${data.guildId})")
    }

    override suspend fun onGuildMemberUpdate(data: GuildMemberEvent) {}

    override suspend fun onGuildMemberRemove(data: GuildMemberEvent) {
        Constant.logger.info("${data.user.username}(${data.user.id}) 退出了 ${BotApi.getGuildById(data.guildId).name}(${data.guildId})")
    }

    override suspend fun onAtMessageCreate(data: AtMessageCreateEvent) {
        Constant.logger.info(
            "${BotApi.getGuildById(data.guildId).name}(${data.guildId}) - ${
                BotApi.getChannelInfo(
                    data.channelId
                ).name
            }(${data.channelId}) - ${data.author.username}(${data.author.id}): ${data.content}"
        )
    }

    override suspend fun onMessageCreate(data: MessageCreateEvent) {
        if (data.content.indexOf("<@!${bot!!.id}>") < 0) {
            Constant.logger.info(
                "${BotApi.getGuildById(data.guildId).name}(${data.guildId}) - ${
                    BotApi.getChannelInfo(
                        data.channelId
                    ).name
                }(${data.channelId}) - ${data.author.username}(${data.author.id}):${
                    if (null != data.attachments && data.attachments.isNotEmpty()) Gson().toJson(
                        data.attachments
                    ) else ""
                } ${data.content}"
            )
        }
    }

    override suspend fun onChannelCreate(data: ChannelEvent) {
        Constant.logger.info("${BotApi.getGuildById(data.guildId).name}(${data.guildId}) - ${data.name}(${data.id}) 子频道被创建")
    }

    override suspend fun onChannelUpdate(data: ChannelEvent) {
        Constant.logger.info("${BotApi.getGuildById(data.guildId).name}(${data.guildId}) - ${data.name}(${data.id}) 子频道信息已被修改")
    }

    override suspend fun onChannelDelete(data: ChannelEvent) {
        Constant.logger.info("${BotApi.getGuildById(data.guildId).name}(${data.guildId}) - ${data.name}(${data.id}) 子频道被删除")
    }

    override suspend fun onGuildCreate(data: GuildEvent) {
        Constant.logger.info("机器人加入了 ${data.name}(${data.id}) 介绍: ${data.description}")
    }

    override suspend fun onGuildUpdate(data: GuildEvent) {
        Constant.logger.info("${data.name}(${data.id}) 频道信息变更")

    }

    override suspend fun onGuildDelete(data: GuildEvent) {
        Constant.logger.info("机器人离开了 ${data.name}(${data.id})")
    }

    override suspend fun onMessageReactionAdd(data: MessageReactionEvent) {
        val memberInfo = BotApi.getMemberInfo(data.guildId, data.userId)
        Constant.logger.info(
            "${BotApi.getGuildById(data.guildId).name}(${data.guildId}) - ${
                BotApi.getChannelInfo(
                    data.channelId
                ).name
            }(${data.channelId}) - ${memberInfo.user!!.username}(${data.userId}): 添加了表情表态对象: ${data.emoji} ${
                getInfo(
                    data.emoji.id
                )
            }"
        )
    }

    override suspend fun onMessageReactionRemove(data: MessageReactionEvent) {
        val memberInfo = BotApi.getMemberInfo(data.guildId, data.userId)
        Constant.logger.info(
            "${BotApi.getGuildById(data.guildId).name}(${data.guildId}) - ${
                BotApi.getChannelInfo(
                    data.channelId
                ).name
            }(${data.channelId}) - ${memberInfo.user!!.username}(${data.userId}): 删除了表情表态对象: ${data.emoji} ${
                getInfo(
                    data.emoji.id
                )
            }"
        )
    }


    override suspend fun onAudioStart(data: AudioActionEvent) {
        Constant.logger.info(
            "${BotApi.getGuildById(data.guildId).name}(${data.guildId}) - ${
                BotApi.getChannelInfo(
                    data.channelId
                ).name
            }(${data.channelId}) - ${data.text} -${data.audioUrl}"
        )
    }

    override suspend fun onAudioFinish(data: AudioActionEvent) {
        Constant.logger.info(
            "${BotApi.getGuildById(data.guildId).name}(${data.guildId}) - ${
                BotApi.getChannelInfo(
                    data.channelId
                ).name
            }(${data.channelId}) - ${data.text} -${data.audioUrl}"
        )
    }

    override suspend fun onAudioOnMic(data: AudioActionEvent) {
        Constant.logger.info(
            "${BotApi.getGuildById(data.guildId).name}(${data.guildId}) - ${
                BotApi.getChannelInfo(
                    data.channelId
                ).name
            }(${data.channelId}) - 上麦了"
        )

    }

    override suspend fun onAudioOffMic(data: AudioActionEvent) {
        Constant.logger.info(
            "${BotApi.getGuildById(data.guildId).name}(${data.guildId}) - ${
                BotApi.getChannelInfo(
                    data.channelId
                ).name
            }(${data.channelId}) - 下麦了"
        )

    }
}

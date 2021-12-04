package com.hcyacg.protocol.common

import com.google.gson.Gson
import com.hcyacg.protocol.constant.Constant
import com.hcyacg.protocol.event.MessageCreateEvent

internal class MonitorMessage:BotEvent(){

    override suspend fun onMessageCreate(data: MessageCreateEvent) {
        Constant.logger.info("${BotApi.getGuildById(data.guild_id).name}(${data.guild_id}) - ${BotApi.getChannelInfo(data.channel_id).name}(${data.channel_id}) - ${data.author.username}(${data.author.id}):${if(data.attachments.isNotEmpty())  Gson().toJson(data.attachments) else ""} ${data.content}")
    }
}

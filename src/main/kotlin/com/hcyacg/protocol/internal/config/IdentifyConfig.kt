package com.hcyacg.protocol.internal.config

import com.hcyacg.protocol.internal.entity.D
import com.hcyacg.protocol.internal.entity.Properties


data class IdentifyConfig(
    val token: String,
    val shards: Int = 1,
    var index: Int = 0,
    val intents: Intents = Intents(),
    val properties: Properties = Properties()
) {
    fun toIdentifyOperationData(): D {
        return D(
            intents = intents.toIntentsValue(),
            properties = properties,
            shard = mutableListOf(index, shards),
            token = token
        )
    }
}


data class Intents(
    val guilds: Boolean = true,
    val guildMembers: Boolean = true,
    val directMessage: Boolean = true,
    val audioAction: Boolean = true,
    val forum: Boolean = true,
    val atMessages: Boolean = true,
    val messages: Boolean = false,
    val guildMessageReactions: Boolean = true,
    val messageAudit:Boolean = true
) {
    fun toIntentsValue(): Long {
        return ((if (guilds) 1.shl(0) else 0)
                + (if (guildMembers) 1.shl(1) else 0)
                + (if (messages) 1.shl(9) else 0)
                + (if (guildMessageReactions) 1.shl(10) else 0)
                + (if (directMessage) 1.shl(12) else 0)
                + (if (messageAudit) 1.shl(27) else 0)
                + (if (forum) 1.shl(28) else 0)
                + (if (audioAction) 1.shl(29) else 0)
                + (if (atMessages) 1.shl(30) else 0))
            .toLong()
    }
}
package com.hcyacg.protocol.entity
import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg

@NoArg
open class MessageAudited {
    @SerializedName("audit_id")
    lateinit var auditId: String
    @SerializedName("audit_time")
    lateinit var auditTime: String
    @SerializedName("channel_id")
    lateinit var channelId: String
    @SerializedName("create_time")
    lateinit var createTime: String
    @SerializedName("guild_id")
    lateinit var guildId: String
    @SerializedName("message_id")
    lateinit var messageId: String
}
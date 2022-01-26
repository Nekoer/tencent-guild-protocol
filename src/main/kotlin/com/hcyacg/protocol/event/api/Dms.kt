package com.hcyacg.protocol.event.api
import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg

@NoArg
data class Dms(
    @SerializedName("channel_id")
    val channelId: String,
    @SerializedName("create_time")
    val createTime: String,
    @SerializedName("guild_id")
    val guildId: String
)

@NoArg
data class DmsDto(
    @SerializedName("recipient_id")
    val recipient_id : String,
    @SerializedName("source_guild_id")
    val source_guild_id: String
)
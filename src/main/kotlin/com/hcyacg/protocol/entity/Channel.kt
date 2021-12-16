package com.hcyacg.protocol.entity

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class Channel(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("guild_id")
    val guildId: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("type")
    val type: Int = 0,
    @SerializedName("sub_type")
    val subType: Int = 0,
    @SerializedName("position")
    val position: Int = 0,
    @SerializedName("parent_id")
    val parentId: String = "",
    @SerializedName("owner_id")
    val ownerId: String = ""
)

/**
 * 子频道种类
 */
@Serializable
object ChannelType {

    /**
     * 文字子频道
     */
    val textSubchannel = 0

    /**
     * 保留，不可用
     */
    val unavailable1 = 1

    /**
     * 语音子频道
     */
    val voiceSubchannel = 2

    /**
     * 保留，不可用
     */
    val unavailable3 = 3

    /**
     * 子频道分组
     */
    val subchannelGrouping = 4

    /**
     * 直播子频道
     */
    val liveSubchannel = 10005

    /**
     * 应用子频道
     */
    val applicationSubchannel = 10006

    /**
     * 论坛子频道
     */
    val forumSubchannel = 10007

}

/**
 * 文字子频道种类
 */
@Serializable
object ChannelSubType {

    /**
     * 闲聊
     */
    val smallTalk = 0

    /**
     * 公告
     */
    val announcement = 1

    /**
     * 攻略
     */
    val strategy = 2

    /**
     * 开黑
     */
    val openBlack = 3


}

@Serializable
@NoArg
data class ChannelDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: Int,
    @SerializedName("position")
    val position: Int,
    @SerializedName("parent_id")
    val parentId: String
)

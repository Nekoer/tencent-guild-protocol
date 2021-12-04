package com.hcyacg.protocol.entity

import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class Channel(
    @SerialName("id")
    val id: String = "",
    @SerialName("guild_id")
    val guild_id: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("type")
    val type: Int = 0,
    @SerialName("sub_type")
    val sub_type: Int = 0,
    @SerialName("position")
    val position: Int = 0,
    @SerialName("parent_id")
    val parent_id: String = "",
    @SerialName("owner_id")
    val owner_id: String = ""
)

/**
 * 子频道种类
 */
@Serializable
object ChannelType{

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
object ChannelSubType{

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
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: Int,
    @SerialName("position")
    val position: Int,
    @SerialName("parent_id")
    val parent_id: String
)

package com.hcyacg.protocol.internal.enums

/**
 * 事件种类
 */
enum class DispatchEnums {
    READY,                 // 准备
    RESUMED,               // 恢复
    GUILD_MEMBER_ADD,      // 新用户加入频道
    GUILD_MEMBER_UPDATE,   // 暂无
    GUILD_MEMBER_REMOVE,   // 用户离开频道
    GUILD_CREATE,          // 当机器人加入新guild时
    GUILD_UPDATE,          // 当guild资料发生变更时
    GUILD_DELETE,          // 当机器人退出guild时
    CHANNEL_CREATE,        // 子频道被创建
    CHANNEL_UPDATE,        // 子频道信息变更
    CHANNEL_DELETE,        // 子频道被删除
    AT_MESSAGE_CREATE,     // 艾特机器人
    MESSAGE_CREATE,        // 私域不需要艾特
    AUDIO_START,           // 音频开始播放时
    AUDIO_FINISH,          // 音频播放结束时
    AUDIO_ON_MIC,          // 上麦时
    AUDIO_OFF_MIC,          // 下麦时
}
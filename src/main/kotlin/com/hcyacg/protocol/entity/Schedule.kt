package com.hcyacg.protocol.entity

import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 字段名	         类型	描述
    id	             string	日程 id
    name	         string	日程名称
    description	     string	日程描述
    start_timestamp	 string	日程开始时间戳(ms)
    end_timestamp	 string	日程结束时间戳(ms)
    creator	         Member	创建者
    jump_channel_id	 string	日程开始时跳转到的子频道 id
    remind_type	     string	日程提醒类型，取值参考RemindType
 */
@Serializable
@NoArg
data class Schedule(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name:String,
    @SerialName("description")
    val description:String,
    @SerialName("start_timestamp")
    val start_timestamp:String,
    @SerialName("end_timestamp")
    val end_timestamp:String,
    @SerialName("creator")
    val creator:Member,
    @SerialName("jump_channel_id")
    val jump_channel_id:String,
    @SerialName("remind_type")
    val remind_type:String,
)

/**
 * 提醒类型 id	描述
     0	       不提醒
     1	       开始时提醒
     2	       开始前5分钟提醒
     3	       开始前15分钟提醒
     4	       开始前30分钟提醒
     5	       开始前60分钟提醒
 */
@Serializable
class RemindType{
    companion object {
        /**
         * 不提醒
         */
        const val NO_REMINDER: String =  "0"

        /**
         * 开始时提醒
         */
        const val START_WITH_REMINDER : String = "1"

        /**
         * 开始前5分钟提醒
         */
        const val FIVE_MINUTES_BEFORE_THE_START : String = "2"

        /**
         * 开始前10分钟提醒
         */
        const val TEN_MINUTES_BEFORE_THE_START : String = "3"

        /**
         * 开始前30分钟提醒
         */
        const val THIRTY_MINUTES_BEFORE_THE_START : String = "4"

        /**
         * 开始前60分钟提醒
         */
        const val SIXTY_MINUTES_BEFORE_THE_START : String = "5"
    }
}
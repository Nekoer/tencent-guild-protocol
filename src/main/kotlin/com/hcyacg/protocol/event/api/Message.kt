package com.hcyacg.protocol.event.api

import com.hcyacg.protocol.utils.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Message(
    @SerialName("content")
    val content: String,     //消息内容，文本内容，支持内嵌格式
    @SerialName("embed")
    val embed: MessageEmbed, //embed 消息，一种特殊的 ark
    @SerialName("ark")
    val ark: MessageArk,     //ark 消息
    @SerialName("image")
    val image: String,       //图片url地址
    @SerialName("msg_id")
    val msg_id: String        //要回复的消息id(Message.id), 在 AT_CREATE_MESSAGE 事件中获取。带了 msg_id 视为被动回复消息，否则视为主动推送消息
)

@Serializable
data class TextMessage(
    @SerialName("content")
    val content: String,     //消息内容，文本内容，支持内嵌格式
    @SerialName("msg_id")
    val msg_id: String        //要回复的消息id(Message.id), 在 AT_CREATE_MESSAGE 事件中获取。带了 msg_id 视为被动回复消息，否则视为主动推送消息
)

@Serializable
data class initiativeTextMessage(
    @SerialName("content")
    val content: String     //消息内容，文本内容，支持内嵌格式
)

@Serializable
data class TextWithImageMessage(
    @SerialName("content")
    val content: String,     //消息内容，文本内容，支持内嵌格式
    @SerialName("image")
    val image: String,       //图片url地址
    @SerialName("msg_id")
    val msg_id: String        //要回复的消息id(Message.id), 在 AT_CREATE_MESSAGE 事件中获取。带了 msg_id 视为被动回复消息，否则视为主动推送消息
)

@Serializable
data class initiativeTextWithImageMessage(
    @SerialName("content")
    val content: String,     //消息内容，文本内容，支持内嵌格式
    @SerialName("image")
    val image: String       //图片url地址
)


@Serializable
data class ImageMessage(
    @SerialName("image")
    val image: String,       //图片url地址
    @SerialName("msg_id")
    val msg_id: String        //要回复的消息id(Message.id), 在 AT_CREATE_MESSAGE 事件中获取。带了 msg_id 视为被动回复消息，否则视为主动推送消息
)
@Serializable
data class initiativeImageMessage(
    @SerialName("image")
    val image: String,       //图片url地址
)


@Serializable
data class ArkMessage(
    @SerialName("ark")
    val ark: MessageArk,     //ark 消息
    @SerialName("msg_id")
    val msg_id: String        //要回复的消息id(Message.id), 在 AT_CREATE_MESSAGE 事件中获取。带了 msg_id 视为被动回复消息，否则视为主动推送消息
)

@Serializable
data class initiativeArkMessage(
    @SerialName("ark")
    val ark: MessageArk     //ark 消息
)


@Serializable
data class EmbedMessage(
    @SerialName("embed")
    val embed: MessageEmbed, //embed 消息，一种特殊的 ark
    @SerialName("msg_id")
    val msg_id: String        //要回复的消息id(Message.id), 在 AT_CREATE_MESSAGE 事件中获取。带了 msg_id 视为被动回复消息，否则视为主动推送消息
)


@Serializable
data class initiativeEmbedMessage(
    @SerialName("embed")
    val embed: MessageEmbed, //embed 消息，一种特殊的 ark
)


@Serializable
data class MessageEmbed(
    @SerialName("title")
    val title: String,            //标题
    @SerialName("description")
    val description: String,      //描述
    @SerialName("prompt")
    val prompt: String,           //消息弹窗内容
    @SerialName("timestamp")
    @Serializable(with = LocalDateTimeSerializer::class)
    val timestamp: LocalDateTime, //消息创建时间
    @SerialName("fields")
    val fields: MessageEmbedField //消息创建时间
)

@Serializable
data class MessageEmbedField(
    @SerialName("name")
    val name: String,
    @SerialName("value")
    val value: String
)

@Serializable
data class MessageAttachment(
    @SerialName("url")
    val url: String //下载地址
)

@Serializable
data class MessageArk(
    @SerialName("template_id")
    val template_id:Int,
    @SerialName("kv")
    val kv:List<MessageArkKv>

)

@Serializable
data class MessageArkKv(
    @SerialName("key")
    val key:String,
    @SerialName("value")
    val value:String?,
    @SerialName("obj")
    val obj:List<MessageArkObj>?
)

@Serializable
data class MessageArkObj(
    @SerialName("obj_kv")
    val obj_kv:List<MessageArkObjKv>
)

@Serializable
data class MessageArkObjKv(
    @SerialName("key")
    val key:String,
    @SerialName("value")
    val value:String
)
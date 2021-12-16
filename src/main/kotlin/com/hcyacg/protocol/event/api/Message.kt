package com.hcyacg.protocol.event.api

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.utils.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Message(
    @SerializedName("content")
    val content: String,     //消息内容，文本内容，支持内嵌格式
    @SerializedName("embed")
    val embed: MessageEmbed, //embed 消息，一种特殊的 ark
    @SerializedName("ark")
    val ark: MessageArk,     //ark 消息
    @SerializedName("image")
    val image: String,       //图片url地址
    @SerializedName("msg_id")
    val msgId: String        //要回复的消息id(Message.id), 在 AT_CREATE_MESSAGE 事件中获取。带了 msg_id 视为被动回复消息，否则视为主动推送消息
)

@Serializable
data class TextMessage(
    @SerializedName("content")
    val content: String,     //消息内容，文本内容，支持内嵌格式
    @SerializedName("msg_id")
    val msgId: String        //要回复的消息id(Message.id), 在 AT_CREATE_MESSAGE 事件中获取。带了 msg_id 视为被动回复消息，否则视为主动推送消息
)

@Serializable
data class initiativeTextMessage(
    @SerializedName("content")
    val content: String     //消息内容，文本内容，支持内嵌格式
)

@Serializable
data class TextWithImageMessage(
    @SerializedName("content")
    val content: String,     //消息内容，文本内容，支持内嵌格式
    @SerializedName("image")
    val image: String,       //图片url地址
    @SerializedName("msg_id")
    val msgId: String        //要回复的消息id(Message.id), 在 AT_CREATE_MESSAGE 事件中获取。带了 msg_id 视为被动回复消息，否则视为主动推送消息
)

@Serializable
data class initiativeTextWithImageMessage(
    @SerializedName("content")
    val content: String,     //消息内容，文本内容，支持内嵌格式
    @SerializedName("image")
    val image: String       //图片url地址
)


@Serializable
data class ImageMessage(
    @SerializedName("image")
    val image: String,       //图片url地址
    @SerializedName("msg_id")
    val msgId: String        //要回复的消息id(Message.id), 在 AT_CREATE_MESSAGE 事件中获取。带了 msg_id 视为被动回复消息，否则视为主动推送消息
)

@Serializable
data class initiativeImageMessage(
    @SerializedName("image")
    val image: String,       //图片url地址
)


@Serializable
data class ArkMessage(
    @SerializedName("ark")
    val ark: MessageArk,     //ark 消息
    @SerializedName("msg_id")
    val msgId: String        //要回复的消息id(Message.id), 在 AT_CREATE_MESSAGE 事件中获取。带了 msg_id 视为被动回复消息，否则视为主动推送消息
)

@Serializable
data class initiativeArkMessage(
    @SerializedName("ark")
    val ark: MessageArk     //ark 消息
)


@Serializable
data class EmbedMessage(
    @SerializedName("embed")
    val embed: MessageEmbed, //embed 消息，一种特殊的 ark
    @SerializedName("msg_id")
    val msgId: String        //要回复的消息id(Message.id), 在 AT_CREATE_MESSAGE 事件中获取。带了 msg_id 视为被动回复消息，否则视为主动推送消息
)


@Serializable
data class initiativeEmbedMessage(
    @SerializedName("embed")
    val embed: MessageEmbed, //embed 消息，一种特殊的 ark
)

/**
 * embed
 *@param title    标题
 *@param description    描述
 *@param prompt    消息弹窗内容
 *@param timestamp    消息创建时间
 *@param fields    MessageEmbedField 对象数组	消息创建时间
 */
@Serializable
data class MessageEmbed(
    @SerializedName("title")
    val title: String,            //标题
    @SerializedName("description")
    val description: String,      //描述
    @SerializedName("prompt")
    val prompt: String,           //消息弹窗内容
    @SerializedName("timestamp")
    @Serializable(with = LocalDateTimeSerializer::class)
    val timestamp: LocalDateTime, //消息创建时间
    @SerializedName("fields")
    val fields: MessageEmbedField //消息创建时间
)

/**
 *@param name    字段名
 *@param value    字段值
 */
@Serializable
data class MessageEmbedField(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
)

/**
 *@param url    下载地址
 */
@Serializable
data class MessageAttachment(
    @SerializedName("url")
    val url: String //下载地址
)

/**
 * @param templateId    ark模板id（需要先申请） 23 链接+文本列表模板 24 文本+缩略图模板 37 大图模板
 * @param kv    MessageAkrKv arkkv数组	kv值列表
 */
@Serializable
data class MessageArk(
    @SerializedName("template_id")
    val templateId: Int,
    @SerializedName("kv")
    val kv: List<MessageArkKv>
)

/**
 *@param key    key
 *@param value    value
 *@param obj MessageArkObj arkobj类型的数组	ark obj类型的列表
 */
@Serializable
data class MessageArkKv(
    @SerializedName("key")
    val key: String,
    @SerializedName("value")
    val value: String?,
    @SerializedName("obj")
    val obj: List<MessageArkObj>?
)

/**
 *@param objKv    MessageArkObjKv objkv类型的数组	ark objkv列表
 */
@Serializable
data class MessageArkObj(
    @SerializedName("obj_kv")
    val objKv: List<MessageArkObjKv>
)

/**
 *@param key    key
 *@param value    value
 */
@Serializable
data class MessageArkObjKv(
    @SerializedName("key")
    val key: String,
    @SerializedName("value")
    val value: String
)
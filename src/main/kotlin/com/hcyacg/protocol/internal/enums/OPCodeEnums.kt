package com.hcyacg.protocol.internal.enums

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/*
CODE	名称	            客户端操作	  描述
0	    Dispatch	    Receive	      服务端进行消息推送
1	    Heartbeat    	Send/Receive  客户端或服务端发送心跳
2	    Identify    	Send	      客户端发送鉴权
6	    Resume	        Send	      客户端回复会话
7	    Reconnect    	Receive	      服务端通知客户端重新连接
9	    Invalid Session	Receive	      当identify或resume的时候，如果参数有错，服务端会返回该消息
11	    Heartbeat ACK	Receive	      当发送心跳成功之后，就会收到该消息
 */
@Serializable
enum class OPCodeEnums(
    val code:Int,
    val description:String,
){

    @SerialName("Dispatch")
    DISPATCH(0,"服务端进行消息推送"),
    @SerialName("Heartbeat")
    HEARTBEAT(1,"客户端或服务端发送心跳"),
    @SerialName("Identify")
    IDENTIFY(2,"客户端发送鉴权"),
    @SerialName("Resume")
    RESUME(6,"客户端回复会话"),
    @SerialName("Reconnect")
    RECONNECT(7,"服务端通知客户端重新连接"),
    @SerialName("Invalid_Session")
    INVALID_SESSION(9,"当identify或resume的时候，如果参数有错，服务端会返回该消息"),
    @SerialName("Hello")
    HELLO(10,"心跳参数"),
    @SerialName("Heartbeat_ACK")
    HEARTBEAT_ACK(11,"当发送心跳成功之后，就会收到该消息"),
    UNKNOWN(-1,"未知")
    ;

    companion object{
        private val codeMap = values().associateBy { it.code }
        fun getOPCodeByCode(code: Int): OPCodeEnums {
            return codeMap[code]?: UNKNOWN
        }
    }
    object OperationKSerializer : KSerializer<OPCodeEnums>{
        override fun deserialize(decoder: Decoder): OPCodeEnums {
            return getOPCodeByCode(decoder.decodeInt())
        }

        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("OPCodeEnums", PrimitiveKind.INT)

        override fun serialize(encoder: Encoder, value: OPCodeEnums) {
            encoder.encodeInt(value.code)
        }

    }
}
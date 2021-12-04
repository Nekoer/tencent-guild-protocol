package com.hcyacg.protocol.internal.entity
import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.Serializable

/**
 * 获取通用 WSS 接入点 时返回的实体对象
 * 段名	                  类型	              描述
 * url	                  string	          WebSocket 的连接地址
 */
@Serializable
@NoArg
data class UniversalWssAccess(
    @SerializedName("url")
    var url: String = ""
)
package com.hcyacg.protocol.internal.entity
import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.Serializable

import com.google.gson.annotations.SerializedName


/**
 * 获取带分片 WSS 接入点 时返回的实体对象
 * 字段名	              类型	              描述
 * url	                  string	          WebSocket 的连接地址
 * shards	              int	              建议的 shard 数
 * session_start_limit	  SessionStartLimit	  创建Session限制信息
 *
 */
@Serializable
@NoArg
data class AccessWithFragmentedWss(
    @SerializedName("session_start_limit")
    var session_start_limit: SessionStartLimit,
    @SerializedName("shards")
    var shards: Int,
    @SerializedName("url")
    var url: String
)

/**
 * 字段名	        类型	  描述
 * total	        int	  每 24 小时可创建 Session 数
 * remaining	    int	  目前还可以创建的 Session 数
 * reset_after	    int	  重置计数的剩余时间(ms)
 * max_concurrency	int	  每 5s 可以创建的 Session 数
 */
@Serializable
@NoArg
data class SessionStartLimit(
    @SerializedName("max_concurrency")
    var max_concurrency: Int,
    @SerializedName("remaining")
    var remaining: Int,
    @SerializedName("reset_after")
    var reset_after: Int,
    @SerializedName("total")
    var total: Int
)
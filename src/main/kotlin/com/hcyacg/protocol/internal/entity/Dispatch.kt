package com.hcyacg.protocol.internal.entity

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.internal.enums.DispatchEnums


data class Dispatch<T>(
    @SerializedName("s")
    val seq: Long,
    @SerializedName("t")
    val type: DispatchEnums,
    @SerializedName("d")
    val d: T
) : Operation(0)


data class DispatchType(
    @SerializedName("t")
    val type: DispatchEnums,
    @SerializedName("s")
    val seq: Long,
)
package com.hcyacg.protocol.internal.entity

import com.hcyacg.protocol.internal.enums.DispatchEnums
import com.hcyacg.protocol.internal.enums.OPCodeEnums
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class Dispatch<T>(
    @SerialName("s")
    val seq: Long,
    @SerialName("t")
    val type: DispatchEnums,
    @SerialName("d")
    val d: T
) : Operation(OPCodeEnums.DISPATCH)

@Serializable
data class DispatchType(
    @SerialName("t")
    val type: DispatchEnums,
    @SerialName("s")
    val seq: Long,
)
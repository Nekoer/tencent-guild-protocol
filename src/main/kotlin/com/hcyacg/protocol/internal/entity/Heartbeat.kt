package com.hcyacg.protocol.internal.entity

import com.hcyacg.protocol.internal.enums.OPCodeEnums
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Heartbeat(
    @SerialName("d")
    val count:Long
): Operation(OPCodeEnums.HEARTBEAT)
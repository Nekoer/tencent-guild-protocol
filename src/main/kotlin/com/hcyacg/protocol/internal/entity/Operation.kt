package com.hcyacg.protocol.internal.entity

import com.hcyacg.protocol.internal.enums.OPCodeEnums
import kotlinx.serialization.Serializable

@Serializable
open class Operation(
    @Serializable(with = OPCodeEnums.OperationKSerializer::class)
    val op:OPCodeEnums
)
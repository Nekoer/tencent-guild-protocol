package com.hcyacg.protocol.internal.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
open class Operation(
    @SerializedName("op")
    val op: Int
)

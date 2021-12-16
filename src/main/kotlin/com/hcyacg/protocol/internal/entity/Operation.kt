package com.hcyacg.protocol.internal.entity

import com.google.gson.annotations.SerializedName


open class Operation(
    @SerializedName("op")
    val op: Int
)

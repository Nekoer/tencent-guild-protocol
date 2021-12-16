package com.hcyacg.protocol.internal.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class Heartbeat(
    @SerializedName("d")
    val count: Long
) : Operation(1)
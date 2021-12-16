package com.hcyacg.protocol.event.api

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.utils.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.*

@Serializable
data class Member(
    @SerializedName("joined_at")
    val joinedAt: String,
    @SerializedName("roles")
    val roles: List<String>
)

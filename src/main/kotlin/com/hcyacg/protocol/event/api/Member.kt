package com.hcyacg.protocol.event.api

import com.hcyacg.protocol.utils.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Member(
    @SerialName("joined_at")
    @Serializable( with = LocalDateTimeSerializer::class )
    val joined_at: LocalDateTime,
    @SerialName("roles")
    val roles: List<String>
)

package com.hcyacg.protocol.internal.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class Resume(
    val d: ResumeData
) : Operation(6)

@Serializable
data class ResumeData(
    @SerializedName("seq")
    val seq: Long,
    @SerializedName("session_id")
    val sessionId: String,
    @SerializedName("token")
    val token: String
)
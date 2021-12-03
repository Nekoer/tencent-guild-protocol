package com.hcyacg.protocol.internal.entity

import com.hcyacg.protocol.internal.enums.OPCodeEnums
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Resume(
    val d:ResumeData
):Operation(OPCodeEnums.RESUME)

@Serializable
data class ResumeData(
    @SerialName("seq")
    val seq: Long,
    @SerialName("session_id")
    val sessionId: String,
    @SerialName("token")
    val token: String
)
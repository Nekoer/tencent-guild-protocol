package com.hcyacg.protocol.internal.entity

import com.google.gson.annotations.SerializedName



data class Resume(
    @SerializedName("d")
    val d: ResumeData
) : Operation(6)


data class ResumeData(
    @SerializedName("seq")
    val seq: Long,
    @SerializedName("session_id")
    val sessionId: String,
    @SerializedName("token")
    val token: String
)
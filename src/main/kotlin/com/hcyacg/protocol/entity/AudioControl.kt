package com.hcyacg.protocol.entity

import com.google.gson.annotations.SerializedName
import com.hcyacg.protocol.anno.NoArg


@NoArg
data class AudioControl(
    @SerializedName("audio_url")
    val audioUrl: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("status")
    val status: Int
)
package com.hcyacg.protocol.entity

import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class AudioControl(
    @SerialName("audio_url")
    val audio_url: String,
    @SerialName("text")
    val text: String,
    @SerialName("status")
    val status: Int
)
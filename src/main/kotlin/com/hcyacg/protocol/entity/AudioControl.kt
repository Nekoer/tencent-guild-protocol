package com.hcyacg.protocol.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AudioControl (
    @SerialName("audio_url")
    val audio_url:String,
    @SerialName("text")
    val text:String,
    @SerialName("status")
    val status:Int
)
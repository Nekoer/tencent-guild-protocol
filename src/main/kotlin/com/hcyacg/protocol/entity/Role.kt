package com.hcyacg.protocol.entity

import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class Role (
    @SerialName("id")
    val id:String,
    @SerialName("name")
    val name:String,
    @SerialName("color")
    val color:Long,
    @SerialName("hoist")
    val hoist:Long,
    @SerialName("number")
    val number:Long,
    @SerialName("number_limit")
    val number_limit:Int,
)
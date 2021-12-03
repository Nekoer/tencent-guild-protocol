package com.hcyacg.protocol.entity

import com.hcyacg.protocol.anno.NoArg
import com.hcyacg.protocol.utils.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
@NoArg
data class Guild (
    @SerialName("id")
    val id:String,
    @SerialName("name")
    val name:String,
    @SerialName("icon")
    val icon:String,
    @SerialName("owner_id")
    val owner_id:String,
    @SerialName("owner")
    val owner:Boolean,
    @SerialName("member_count")
    val member_count:Int,
    @SerialName("max_members")
    val max_members:Int,
    @SerialName("description")
    val description:String,
    @SerialName("joined_at")
    @Serializable( with = LocalDateTimeSerializer::class )
    val joined_at:LocalDateTime,
)
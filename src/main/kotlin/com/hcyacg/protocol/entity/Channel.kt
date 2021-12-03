package com.hcyacg.protocol.entity

import com.hcyacg.protocol.anno.NoArg
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@NoArg
data class Channel(
    @SerialName("id")
    val id: String ="",
    @SerialName("guild_id")
    val guild_id: String="",
    @SerialName("name")
    val name: String="",
    @SerialName("type")
    val type: Int=0,
    @SerialName("sub_type")
    val sub_type: Int=0,
    @SerialName("position")
    val position: Int =0,
    @SerialName("parent_id")
    val parent_id: String="",
    @SerialName("owner_id")
    val owner_id: String=""
)


package com.hcyacg.protocol.event.message

import com.hcyacg.protocol.anno.NoArg


@NoArg
class AtMessageCreateEvent : EventApi() {


    fun messageContent(): String {
        return content.replace(Regex("<@!\\d+>"), "")
    }

}
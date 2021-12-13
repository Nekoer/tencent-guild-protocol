package com.hcyacg.protocol.common

import com.hcyacg.protocol.constant.Constant.Companion.accessWithFragmentedWss
import com.hcyacg.protocol.internal.config.IdentifyConfig

open class BotClient(
    config: IdentifyConfig,
    officialEvents:List<BotEvent> = emptyList(),
    uri: String = accessWithFragmentedWss!!.url,
) : SequelBotClient<BotListener>(
    uri = uri,
    BotListener(config,officialEvents)
)
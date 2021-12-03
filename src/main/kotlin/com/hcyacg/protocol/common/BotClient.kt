package com.hcyacg.protocol.common

import com.hcyacg.protocol.internal.config.IdentifyConfig

open class BotClient(
    config: IdentifyConfig,
    officialEvents:List<BotEvent> = emptyList(),
    uri: String = Gateway.gatewayAccessWithFragmentedWss(config.token)!!.url,
) : SequelBotClient<BotListener>(
    uri = uri,
    BotListener(config,officialEvents)
)
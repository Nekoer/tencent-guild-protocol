package com.hcyacg.protocol.constant

import com.hcyacg.protocol.event.api.User
import com.hcyacg.protocol.internal.entity.AccessWithFragmentedWss
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Constant {
    companion object {
        /**
         * 正式环境
         */
        const val proUrl: String = "https://api.sgroup.qq.com"

        /**
         * 沙箱环境
         */
        const val testUrl: String = "https://sandbox.api.sgroup.qq.com"

        /**
         * 网关返回的数据
         */
        var accessWithFragmentedWss: AccessWithFragmentedWss? = null

        /**
         * 机器人token
         */
        var botToken: String? = null

        val logger: Logger = LoggerFactory.getLogger(this::class.java)

        /**
         * 机器人自身信息
         */
        var bot: User? = null
    }
}
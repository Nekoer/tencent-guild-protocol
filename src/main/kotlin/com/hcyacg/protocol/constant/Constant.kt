package com.hcyacg.protocol.constant

import com.google.gson.Gson
import com.hcyacg.protocol.internal.entity.AccessWithFragmentedWss

class Constant {
    companion object {
        /**
         * 正式环境
         */
        const val proUrl:String = "https://api.sgroup.qq.com"

        /**
         * 沙箱环境
         */
        const val testUrl:String = "https://sandbox.api.sgroup.qq.com"

        var accessWithFragmentedWss:AccessWithFragmentedWss? = null

        var botToken:String? = null

    }
}
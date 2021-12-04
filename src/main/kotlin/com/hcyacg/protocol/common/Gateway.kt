package com.hcyacg.protocol.common


import com.google.gson.Gson
import com.hcyacg.protocol.constant.Constant
import com.hcyacg.protocol.constant.Constant.Companion.accessWithFragmentedWss
import com.hcyacg.protocol.constant.Constant.Companion.logger
import com.hcyacg.protocol.internal.entity.AccessWithFragmentedWss
import com.hcyacg.protocol.utils.RequestUtil
import okhttp3.Headers
import okhttp3.RequestBody

class Gateway {

    companion object {
        private val headers = Headers.Builder()
        private var requestBody: RequestBody? = null

        /**
         * 获取wss的地址以及相关数据 封装成对象
         */
        fun gatewayAccessWithFragmentedWss(token:String): AccessWithFragmentedWss? {
            if (null != accessWithFragmentedWss){
                return accessWithFragmentedWss
            }
            headers.add("Authorization",token)
            val data = RequestUtil.requestObject(RequestUtil.Method.GET,
                Constant.proUrl+"/gateway/bot",requestBody,headers.build())
            accessWithFragmentedWss = Gson().fromJson(data.toString(), AccessWithFragmentedWss::class.java)
            logger.info("wss地址已获取 ${accessWithFragmentedWss!!.url}")
            return accessWithFragmentedWss
        }

    }

}
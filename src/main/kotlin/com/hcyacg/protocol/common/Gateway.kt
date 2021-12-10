package com.hcyacg.protocol.common


import com.google.gson.Gson
import com.hcyacg.protocol.constant.Constant
import com.hcyacg.protocol.constant.Constant.Companion.accessWithFragmentedWss
import com.hcyacg.protocol.constant.Constant.Companion.logger
import com.hcyacg.protocol.internal.entity.AccessWithFragmentedWss
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.util.concurrent.TimeUnit

class Gateway {

    companion object {
        private val headers = Headers.Builder()
        private var requestBody: RequestBody? = null

        private val client: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        /**
         * 获取wss的地址以及相关数据 封装成对象
         */
        fun gatewayAccessWithFragmentedWss(token:String): AccessWithFragmentedWss? {
            if (null != accessWithFragmentedWss){
                return accessWithFragmentedWss
            }
            headers.add("Authorization",token)
            val request: Request by lazy { Request.Builder().get().url(Constant.proUrl+"/gateway/bot").header("Authorization",token).build() }
            val execute = client.newCall(request).execute()
            accessWithFragmentedWss = Gson().fromJson(execute.body!!.string(), AccessWithFragmentedWss::class.java)
            logger.info("wss地址已获取 ${accessWithFragmentedWss!!.url}")
            return accessWithFragmentedWss
        }

    }

}
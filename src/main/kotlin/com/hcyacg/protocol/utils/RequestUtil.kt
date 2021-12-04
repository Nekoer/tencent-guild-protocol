package com.hcyacg.protocol.utils

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import okhttp3.*
import java.util.concurrent.TimeUnit

/**
 * http请求
 */
object RequestUtil {

    private val client = OkHttpClient().newBuilder().connectTimeout(60000, TimeUnit.MILLISECONDS)
        .readTimeout(60000, TimeUnit.MILLISECONDS)
    private var response: Response? = null

    fun requestObject(
        method: Method,
        uri: String,
        body: RequestBody?,
        headers: Headers
    ): JSONObject? {
        /**
         * 进行请求转发
         */
        when (method) {
            Method.GET -> {
                return httpObject(Request.Builder().url(uri).headers(headers).get().build())
            }
            Method.POST -> {
                return body?.let { Request.Builder().url(uri).headers(headers).post(it).build() }
                    ?.let { httpObject(it) }
            }
            Method.PUT -> {
                return body?.let { Request.Builder().url(uri).headers(headers).put(it).build() }
                    ?.let { httpObject(it) }
            }
            Method.DEL -> {
                return httpObject(Request.Builder().url(uri).headers(headers).delete(body).build())
            }
        }
    }

    fun requestArray(
        method: Method,
        uri: String,
        body: RequestBody?,
        headers: Headers
    ): JSONArray? {
        /**
         * 进行请求转发
         */
        when (method) {
            Method.GET -> {
                return httpArray(Request.Builder().url(uri).headers(headers).get().build())
            }
            Method.POST -> {
                return body?.let { Request.Builder().url(uri).headers(headers).post(it).build() }
                    ?.let { httpArray(it) }
            }
            Method.PUT -> {
                return body?.let { Request.Builder().url(uri).headers(headers).put(it).build() }
                    ?.let { httpArray(it) }
            }
            Method.DEL -> {
                return httpArray(Request.Builder().url(uri).headers(headers).delete(body).build())
            }
        }

    }


    fun requestHttp(
        method: Method,
        uri: String,
        body: RequestBody?,
        headers: Headers
    ): String? {
        /**
         * 进行请求转发
         */
        when (method) {
            Method.GET -> {
                return http(Request.Builder().url(uri).headers(headers).get().build())
            }
            Method.POST -> {
                return body?.let { Request.Builder().url(uri).headers(headers).post(it).build() }
                    ?.let { http(it) }
            }
            Method.PUT -> {
                return body?.let { Request.Builder().url(uri).headers(headers).put(it).build() }
                    ?.let { http(it) }
            }
            Method.DEL -> {
                return http(Request.Builder().url(uri).headers(headers).delete(body).build())
            }
        }
    }


    /**
     * 发送http请求，返回数据（其中根据proxy是否配置加入代理机制）
     */
    private fun httpObject(request: Request): JSONObject? {


        response = client.build().newCall(request).execute()


        if (response!!.isSuccessful) {
            return JSONObject.parseObject(response!!.body?.string())
        }

        response!!.close()
        return null
    }

    private fun httpArray(request: Request): JSONArray? {

        response = client.build().newCall(request).execute()


        if (response!!.isSuccessful) {
            return JSONArray.parseArray(response!!.body?.string())
        }
        response!!.close()
        return null
    }

    private fun http(request: Request): String {
        response = client.build().newCall(request).execute()
        return response!!.body.toString();
    }

    /**
     * http的请求方式
     */
    enum class Method {
        GET, POST, PUT, DEL
    }

}
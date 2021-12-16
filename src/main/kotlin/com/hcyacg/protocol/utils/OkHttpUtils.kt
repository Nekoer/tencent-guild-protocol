package com.hcyacg.protocol.utils

import com.google.gson.Gson
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.ByteString
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

object OkHttpUtils {
    private val MEDIA_JSON: MediaType = "application/json;charset=utf-8".toMediaType()
    private val MEDIA_STREAM: MediaType = "application/octet-stream".toMediaType()
    private val MEDIA_X_JSON: MediaType = "text/x-json".toMediaType()
    private val MEDIA_ENCRYPTED_JSON: MediaType = "application/encrypted-json;charset=UTF-8".toMediaType()
    private val MEDIA_TEXT: MediaType = "text/plain;charset=UTF-8".toMediaType()
    private const val TIME_OUT = 10L
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().followRedirects(false).followSslRedirects(false).connectTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(10L, TimeUnit.SECONDS).build()
    }

    private fun emptyHeaders(): Headers {
        return addSingleHeader("user-agent", UA.PC.getValue())
    }

    @JvmOverloads
    @Throws(IOException::class)
    operator fun get(url: String, headers: Headers = emptyHeaders()): Response {
        val request: Request = Request.Builder().url(url).headers(headers).build()
        return okHttpClient.newCall(request).execute()
    }

    @Throws(IOException::class)
    operator fun get(url: String, map: Map<String, String>): Response {
        return OkHttpUtils[url, addHeaders(map)]
    }

    @JvmOverloads
    @Throws(IOException::class)
    fun post(url: String, requestBody: RequestBody, headers: Headers = emptyHeaders()): Response {
        val request: Request = Request.Builder().url(url).post(requestBody).headers(headers).build()
        return okHttpClient.newCall(request).execute()
    }

    @JvmName("post1")
    @JvmOverloads
    @Throws(IOException::class)
    fun post(
        url: String,
        map: Map<String, String> = mutableMapOf(),
        headers: Headers = emptyHeaders()
    ): Response {
        return post(url, mapToFormBody(map), headers)
    }

    @Throws(IOException::class)
    fun post(url: String, map: Map<String, String>, headerMap: Map<String, String>): Response {
        return post(url, mapToFormBody(map), addHeaders(headerMap))
    }

    @Throws(IOException::class)
    fun post(url: String, requestBody: RequestBody, headerMap: Map<String, String>): Response {
        return post(url, requestBody, addHeaders(headerMap))
    }

    @Throws(IOException::class)
    fun post(url: String, map: Map<String, String>): Response {
        return post(url, map, emptyHeaders())
    }

    @JvmOverloads
    @Throws(IOException::class)
    fun put(url: String, requestBody: RequestBody, headers: Headers): Response {
        val request: Request = Request.Builder().url(url).put(requestBody).headers(headers).build()
        return okHttpClient.newCall(request).execute()
    }

    @Throws(IOException::class)
    fun put(url: String, map: Map<String, String>): Response {
        return put(url, mapToFormBody(map), emptyHeaders())
    }

    @Throws(IOException::class)
    fun put(url: String, map: Map<String, String>, headers: Map<String, String>): Response {
        return put(url, mapToFormBody(map), addHeaders(headers))
    }

    @Throws(IOException::class)
    fun put(url: String, map: Map<String, String>, headers: Headers): Response {
        return put(url, mapToFormBody(map), headers)
    }

    @Throws(IOException::class)
    private fun delete(url: String, requestBody: RequestBody, headers: Headers = emptyHeaders()): Response {
        val request: Request = Request.Builder().url(url).delete(requestBody).headers(headers).build()
        return okHttpClient.newCall(request).execute()
    }

    @JvmOverloads
    @Throws(IOException::class)
    fun delete(url: String, map: Map<String, String>, headers: Headers = emptyHeaders()): Response {
        return delete(url, mapToFormBody(map), headers)
    }

    @Throws(IOException::class)
    fun delete(url: String, map: Map<String, String>, headerMap: Map<String, String>): Response {
        return delete(url, mapToFormBody(map), addHeaders(headerMap))
    }

    @Throws(IOException::class)
    fun delete(url: String, requestBody: RequestBody, headerMap: Map<String, String>): Response {
        return delete(url, requestBody, addHeaders(headerMap))
    }

    @JvmOverloads
    @Throws(IOException::class)
    fun patch(url: String, requestBody: RequestBody, headers: Headers = emptyHeaders()): Response {
        val request: Request = Request.Builder().url(url).patch(requestBody).headers(headers).build()
        return okHttpClient.newCall(request).execute()
    }

    @JvmOverloads
    @Throws(IOException::class)
    fun patch(url: String, map: Map<String, String>, headers: Headers = emptyHeaders()): Response {
        return patch(url, mapToFormBody(map), headers)
    }

    @Throws(IOException::class)
    fun patch(url: String, map: Map<String, String>, headerMap: Map<String, String>): Response {
        return patch(url, mapToFormBody(map), addHeaders(headerMap))
    }

    @Throws(IOException::class)
    fun patch(url: String, requestBody: RequestBody, headerMap: Map<String, String>): Response {
        return patch(url, requestBody, addHeaders(headerMap))
    }

    @Throws(IOException::class)
    fun getStr(response: Response): String {
        return (Objects.requireNonNull(response.body) as ResponseBody).string()
    }

    @Throws(IOException::class)
    fun getJson(response: Response): String {
        val str = getStr(response)
        return str
    }

    @Throws(IOException::class)
    fun getBytes(url: String): ByteArray {
        return getBytes(url, emptyHeaders())
    }

    @Throws(IOException::class)
    fun getBytes(url: String, headers: Headers): ByteArray {
        val response = OkHttpUtils[url, headers]
        return getBytes(response)
    }

    @Throws(IOException::class)
    fun getBytes(url: String, headers: Map<String, String>): ByteArray {
        val response = OkHttpUtils[url, headers]
        return getBytes(response)
    }

    @Throws(IOException::class)
    fun getBytes(response: Response): ByteArray {
        return (Objects.requireNonNull(response.body) as ResponseBody).bytes()
    }

    fun getByteStream(response: Response): InputStream {
        return (Objects.requireNonNull(response.body) as ResponseBody).byteStream()
    }

    @Throws(IOException::class)
    fun getByteStream(url: String, headers: Headers): InputStream {
        val response = OkHttpUtils[url, headers]
        return getByteStream(response)
    }

    @Throws(IOException::class)
    fun getByteStream(url: String, headers: Map<String, String>): InputStream {
        val response = OkHttpUtils[url, headers]
        return getByteStream(response)
    }

    @Throws(IOException::class)
    fun getByteStream(url: String): InputStream {
        return getByteStream(url, emptyHeaders())
    }

    @Throws(IOException::class)
    private fun getByteStr(response: Response): ByteString {
        return (Objects.requireNonNull(response.body) as ResponseBody).byteString()
    }

    @Throws(IOException::class)
    fun getByteStr(url: String, headers: Headers): ByteString {
        val response = OkHttpUtils[url, headers]
        return getByteStr(response)
    }

    @Throws(IOException::class)
    fun getByteStr(url: String, headers: Map<String, String>): ByteString {
        val response = OkHttpUtils[url, headers]
        return getByteStr(response)
    }

    @Throws(IOException::class)
    fun getByteStr(url: String): ByteString {
        return getByteStr(url, emptyHeaders())
    }

    private fun getIs(response: Response): InputStream {
        return (Objects.requireNonNull(response.body) as ResponseBody).byteStream()
    }

    @Throws(IOException::class)
    fun getStr(url: String, headers: Headers): String {
        val response = OkHttpUtils[url, headers]
        return getStr(response)
    }

    @Throws(IOException::class)
    fun getStr(url: String, headers: Map<String, String>): String {
        val response = OkHttpUtils[url, headers]
        return getStr(response)
    }

    @Throws(IOException::class)
    fun getStr(url: String): String {
        val response = OkHttpUtils[url, emptyHeaders()]
        return getStr(response)
    }

    @Throws(IOException::class)
    fun getJson(url: String, headers: Headers): String {
        val response = OkHttpUtils[url, headers]
        return getJson(response)
    }

    @Throws(IOException::class)
    fun getJson(url: String, map: Map<String, String>): String {
        return getJson(url, addHeaders(map))
    }

    @Throws(IOException::class)
    fun getJson(url: String): String {
        val response = OkHttpUtils[url, emptyHeaders()]
        return getJson(response)
    }

    @Throws(IOException::class)
    fun postStr(url: String, requestBody: RequestBody, headers: Headers): String {
        val response = post(url, requestBody, headers)
        return getStr(response)
    }

    @Throws(IOException::class)
    fun postStr(url: String, requestBody: RequestBody, headers: Map<String, String>): String {
        val response = post(url, requestBody, headers)
        return getStr(response)
    }

    @Throws(IOException::class)
    fun postStr(url: String, requestBody: RequestBody): String {
        val response = post(url, requestBody, emptyHeaders())
        return getStr(response)
    }

    @Throws(IOException::class)
    fun postJson(url: String, requestBody: RequestBody, headers: Headers): String {
        val response = post(url, requestBody, headers)
        return getJson(response)
    }

    @Throws(IOException::class)
    fun postJson(url: String, requestBody: RequestBody, headers: Map<String, String>): String {
        val response = post(url, requestBody, headers)
        return getJson(response)
    }

    @Throws(IOException::class)
    fun postJson(url: String, requestBody: RequestBody): String {
        val response = post(url, requestBody, emptyHeaders())
        return getJson(response)
    }

    private fun mapToFormBody(map: Map<String, String>): RequestBody {
        val builder = FormBody.Builder()
        val var2: Iterator<*> = map.entries.iterator()
        while (var2.hasNext()) {
            val (key, value) = var2.next() as Map.Entry<*, *>
            builder.add(key as String, value as String)
        }
        return builder.build()
    }

    @JvmOverloads
    @Throws(IOException::class)
    fun postStr(url: String, map: Map<String, String>, headers: Headers = emptyHeaders()): String {
        val response = post(url, mapToFormBody(map), headers)
        return getStr(response)
    }

    @Throws(IOException::class)
    fun postStr(url: String, map: Map<String, String>, headers: Map<String, String>): String {
        val response = post(url, mapToFormBody(map), headers)
        return getStr(response)
    }

    @JvmOverloads
    @Throws(IOException::class)
    fun deleteStr(url: String, map: Map<String, String>, headers: Headers = emptyHeaders()): String {
        val response = delete(url, mapToFormBody(map), headers)
        return getStr(response)
    }

    @Throws(IOException::class)
    fun deleteStr(url: String, map: Map<String, String>, headers: Map<String, String>): String {
        val response = delete(url, mapToFormBody(map), headers)
        return getStr(response)
    }

    @Throws(IOException::class)
    fun postJson(url: String, map: Map<String, String>, headers: Headers): String {
        val str = postStr(url, map, headers)
        return Gson().toJson(str)
    }

    @Throws(IOException::class)
    fun postJson(url: String, map: Map<String, String>, headers: Map<String, String>): String {
        val str = postStr(url, map, headers)
        return Gson().toJson(str)
    }

    @Throws(IOException::class)
    fun postJson(url: String, map: Map<String, String>): String {
        val str = postStr(url, map, emptyHeaders())
        return Gson().toJson(str)
    }

    @Throws(IOException::class)
    fun patchJson(url: String, map: Map<String, String>): String {
        val str = getStr(patch(url, map, emptyHeaders()))
        return Gson().toJson(str)
    }

    @Throws(IOException::class)
    fun patchJson(url: String, requestBody: RequestBody, headers: Map<String, String>): String {
        val str = getStr(patch(url, requestBody, headers))
        return Gson().toJson(str)
    }


    @Throws(IOException::class)
    fun deleteJson(url: String, map: Map<String, String>, headers: Headers): String {
        val str = deleteStr(url, map, headers)
        return Gson().toJson(str)
    }

    @Throws(IOException::class)
    fun deleteJson(url: String, map: Map<String, String>, headers: Map<String, String>): String {
        val str = deleteStr(url, map, headers)
        return Gson().toJson(str)
    }

    @Throws(IOException::class)
    fun deleteJson(url: String, map: Map<String, String>): String {
        val str = deleteStr(url, map, emptyHeaders())
        return Gson().toJson(str)
    }



    fun addJson(jsonStr: String): RequestBody {
        return jsonStr.toRequestBody(MEDIA_JSON)
    }

    fun addText(text: String): RequestBody {
        return text.toRequestBody(MEDIA_TEXT)
    }

    fun addBody(text: String, contentType: String): RequestBody {
        val mediaType: MediaType = contentType.toMediaType()
        return text.toRequestBody(mediaType)
    }

    fun addJson(JsonElement: JsonElement): RequestBody {
        return JsonElement.toString().toRequestBody(MEDIA_JSON)
    }

    fun addEncryptedJson(str: String): RequestBody {
        return str.toRequestBody(MEDIA_ENCRYPTED_JSON)
    }

    fun addSingleHeader(name: String, value: String): Headers {
        return Headers.Builder().add(name, value).build()
    }

    fun addHeaders(cookie: String?, referer: String = "", userAgent: String = UA.PC.getValue()): Headers {
        val header = Headers.Builder()
        if (cookie != null) {
            header.add("cookie", cookie)
        }
        return header.add("referer", referer).add("user-agent", userAgent).build()
    }

    fun addHeaders(cookie: String, referer: String, ua: UA): Headers {
        return addHeaders(cookie, referer, ua.getValue())
    }

    fun addHeaders(cookie: String, referer: String): Headers {
        return addHeaders(cookie, referer, UA.PC.getValue())
    }

    fun addHeaders(map: Map<String, String>): Headers {
        val builder = Headers.Builder()
        val var2: Iterator<*> = map.entries.iterator()
        while (var2.hasNext()) {
            val (key, value) = var2.next() as Map.Entry<*, *>
            builder.add(key as String, value as String)
        }
        return builder.build()
    }

    fun addHeader(): Headers.Builder {
        return Headers.Builder()
    }

    fun addUA(ua: UA): Headers {
        return addSingleHeader("user-agent", ua.getValue())
    }

    fun addUA(ua: String): Headers {
        return addSingleHeader("user-agent", ua)
    }

    fun addCookie(cookie: String): Headers {
        return addSingleHeader("cookie", cookie)
    }

    fun addReferer(url: String): Headers {
        return addSingleHeader("referer", url)
    }

    fun addStream(byteString: ByteString): RequestBody {
        return byteString.toRequestBody(MEDIA_STREAM)
    }

    @Throws(IOException::class)
    fun addStream(url: String): RequestBody {
        return addStream(getByteStr(url))
    }


    fun getCookie(cookie: String, name: String): String? {
        var arr = cookie.split("; ").toTypedArray()
        if (arr.isEmpty()) {
            arr = cookie.split(";").toTypedArray()
        }
        val var3 = arr
        val var4 = arr.size
        for (var5 in 0 until var4) {
            val str = var3[var5]
            val newArr = str.split("=").toTypedArray()
            if (newArr.size > 1 && newArr[0].trim { it <= ' ' } == name) {
                return str.substring(str.indexOf(61.toChar()) + 1)
            }
        }
        return null
    }

    fun getCookie(cookie: String, vararg name: String): Map<String, String> {
        return name.toList().map { it to getCookie(cookie, it) }.filter { it.second != null }
            .associate { it.first to it.second!! }
    }

    fun cookieToMap(cookie: String): Map<String, String> {
        val map: MutableMap<String, String> = mutableMapOf()
        val arr = cookie.split(";").toTypedArray()
        val var4 = arr.size
        for (var5 in 0 until var4) {
            val str = arr[var5]
            val newArr = str.split("=").toTypedArray()
            if (newArr.size > 1) {
                map[newArr[0].trim { it <= ' ' }] = newArr[1].trim { it <= ' ' }
            }
        }
        return map
    }

    private fun fileNameByUrl(url: String): String {
        val index = url.lastIndexOf("/")
        return url.substring(index + 1)
    }

    fun getStreamBody(file: File): RequestBody {
        return file.asRequestBody(MEDIA_STREAM)
    }

    fun getStreamBody(file: File, contentType: String): RequestBody {
        return file.asRequestBody(contentType.toMediaType())
    }


    fun getStreamBody(fileName: String, bytes: ByteArray): RequestBody {
        return bytes.toRequestBody(MEDIA_STREAM)
    }

    fun getStreamBody(bytes: ByteArray): RequestBody {
        return getStreamBody(UUID.randomUUID().toString(), bytes)
    }

    fun urlParams(map: Map<String, String>): String {
        val sb = StringBuilder()
        val var2: Iterator<Map.Entry<String, String>> = map.entries.iterator()
        while (var2.hasNext()) {
            val (key, value) = var2.next()
            try {
                sb.append(key).append("=").append(URLEncoder.encode(value, "utf-8")).append("&")
            } catch (var5: UnsupportedEncodingException) {
                var5.printStackTrace()
            }
        }
        return sb.toString()
    }

    fun urlParamsEn(map: Map<String, String>): String {
        val sb = StringBuilder()
        val var2: Iterator<Map.Entry<String, String>> = map.entries.iterator()
        while (var2.hasNext()) {
            val (key, value) = var2.next()
            sb.append(key).append("=").append(value).append("&")
        }
        return sb.toString()
    }

}

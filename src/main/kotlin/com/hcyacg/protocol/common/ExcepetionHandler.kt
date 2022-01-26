package com.hcyacg.protocol.common

import com.google.gson.Gson
import com.google.gson.JsonParser
import okhttp3.Response

internal class ExceptionHandler(response: Response) {

    init {
        val regex = Regex(pattern = "20[0-9]")
        if (regex.containsMatchIn(response.code.toString())) {
            var traceId = response.headers["X-Tps-trace-ID"]
            val message = exceptionCode(response.code)
        }else {
            val result = response.body?.string()
            val code = JsonParser.parseString(result).asJsonObject.get("code").asInt
            val message = exceptionCode(code)
        }
    }


    private fun exceptionCode(code: Int): String? {
        when (code) {
            401 -> return "认证失败"
            404 -> return "未找到 API"
            405 -> return "http method 不允许"
            429 -> return "频率限制"
            500 -> return "处理失败"
            504 -> return "处理失败"
            10001 -> return "UnknownAccount 账号异常"
            10003 -> return "UnknownChannel 子频道异常"
            10004 -> return "UnknownGuild 频道异常"
            11281 -> return "ErrorCheckAdminFailed 检查是否是管理员失败，系统错误，一般重试一次会好，最多只能重试一次"
            11282 -> return "ErrorCheckAdminNotPass 检查是否是管理员未通过，该接口需要管理员权限，但是用户在添加机器人的时候并未授予该权限，属于逻辑错误，可以提示用户进行授权"
            11251 -> return "ErrorWrongAppid 参数中的 appid 错误，开发者填的 token 错误，appid 无法识别"
            11252 -> return "ErrorCheckAppPrivilegeFailed 检查应用权限失败，系统错误，一般重试一次会好，最多只能重试一次"
            11253 -> return "ErrorCheckAppPrivilegeNotPass 检查应用权限不通过，该机器人应用未获得调用该接口的权限，需要向平台申请"
            11254 -> return "ErrorInterfaceForbidden 应用接口被封禁，该机器人虽然获得了该接口权限，但是被封禁了。"
            11261 -> return "ErrorWrongAppid 参数中缺少 appid，同 11251"
            11262 -> return "ErrorCheckRobot 当前接口不支持使用机器人 Bot Token 调用"
            11263 -> return "ErrorCheckGuildAuth 检查频道权限失败，系统错误，一般重试一次会好，最多只能重试一次"
            11264 -> return "ErrorGuildAuthNotPass 检查小站权限未通过，管理员添加机器人的时候未授予该接口权限，属于逻辑错误，可提示用户进行授权"
            11265 -> return "ErrorRobotHasBaned 机器人已经被封禁"
            11241 -> return "ErrorWrongToken 参数中缺少 token"
            11242 -> return "ErrorCheckTokenFailed 校验 token 失败，系统错误，一般重试一次会好，最多只能重试一次"
            11243 -> return "ErrorCheckTokenNotPass 校验 token 未通过，用户填充的 token 错误，需要开发者进行检查"
            11273 -> return "ErrorCheckUserAuth 检查用户权限失败，当前接口不支持使用 Bearer Token 调用"
            11274 -> return "ErrorUserAuthNotPass 检查用户权限未通过，用户 OAuth 授权时未给与该接口权限，可提示用户重新进行授权"
            11275 -> return "ErrorWrongAppid 无 appid ，同 11251"
            12001 -> return "ReplaceIDFailed 替换 id 失败"
            12002 -> return "RequestInvalid 请求体错误"
            12003 -> return "ResponseInvalid 回包错误"
            20028 -> return "ChannelHitWriteRateLimit 子频道消息触发限频"
            50006 -> return "CannotSendEmptyMessage 消息为空"
            50035 -> return "InvalidFormBody form-data 内容异常"
            301000 -> return "参数错误"
            301001 -> return "查询频道信息错误"
            301002 -> return "查询子频道权限错误"
            301003 -> return "修改子频道权限错误"
            301004 -> return "私密子频道关联的人数到达上限"
            301005 -> return "调用Rpc服务失败"
            301006 -> return "非群成员没有查询权限"
            301007 -> return "参数超过数量限制"

            502001 -> return "频道id无效"
            502002 -> return "频道id为空"
            502003 -> return "用户id无效"
            502004 -> return "用户id为空"
            502005 -> return "timestamp不合法"
            502006 -> return "timestamp无效"
            502007 -> return "参数转换错误"
            502008 -> return "rpc调用失败"
            502009 -> return "安全打击"
            502010 -> return "请求头错误"
            304003 -> return "URL_NOT_ALLOWED url 未报备"
            304004 -> return "ARK_NOT_ALLOWED 没有发 ark 消息权限"
            304005 -> return "EMBED_LIMIT embed 长度超限"
            304006 -> return "SERVER_CONFIG 后台配置错误"
            304007 -> return "GET_GUILD 查询频道异常"
            304008 -> return "GET_BOT 查询机器人异常"
            304009 -> return "GET_CHENNAL 查询子频道异常"
            304010 -> return "CHANGE_IMAGE_URL 图片转存错误"
            304011 -> return "NO_TEMPLATE 模板不存在"
            304012 -> return "GET_TEMPLATE 取模板错误"
            304014 -> return "ARK_PRIVILEGE 没有模板权限"
            304016 -> return "SEND_ERROR 发消息错误"
            304017 -> return "UPLOAD_IMAGE 图片上传错误"
            304018 -> return "SESSION_NOT_EXIST 机器人没连上 gateway"
            304019 -> return "AT_EVERYONE_TIMES @全体成员 次数超限"
            304020 -> return "FILE_SIZE 文件大小超限"
            304021 -> return "GET_FILE 下载文件错误"
            304022 -> return "PUSH_TIME 推送消息时间限制"
            304023 -> return "PUSH_MSG_ASYNC_OK 推送消息异步调用成功, 等待人工审核"
            304024 -> return "REPLY_MSG_ASYNC_OK 回复消息异步调用成功, 等待人工审核"
            304025 -> return "BEAT 消息被打击"
            304026 -> return "MSG_ID 回复的消息 id 错误"
            304027 -> return "MSG_EXPIRE 回复的消息过期"
            304028 -> return "MSG_PROTECT 非 At 当前用户的消息不允许回复"
            304029 -> return "CORPUS_ERROR 调语料服务错误"
            304030 -> return "CORPUS_NOT_MATCH 语料不匹配"
            306001 -> return "param invalid 撤回消息参数错误"
            306002 -> return "msgid error 消息id错误"
            306003 -> return "fail to get message 获取消息错误(可重试)"
            306004 -> return "no permission to delete message 没有撤回此消息的权限"
            306005 -> return "retract message error 消息撤回失败(可重试)"
            306006 -> return "fail to get channel 获取子频道失败(可重试)"

            501001 -> return "参数校验失败"
            501002 -> return "创建子频道公告失败(可重试)"
            501003 -> return "删除子频道公告失败(可重试)"
            501004 -> return "获取频道信息失败(可重试)"
            501005 -> return "MessageID 错误"
            501006 -> return "创建频道全局公告失败(可重试)"
            501007 -> return "删除频道全局公告失败(可重试)"
            501008 -> return "MessageID 不存在"
            1100100 -> return "安全打击：消息被限频"
            1100101 -> return "安全打击：内容涉及敏感，请返回修改"
            1100102 -> return "安全打击：抱歉，暂未获得新功能体验资格"
            1100103 -> return "安全打击"
            1100104 -> return "安全打击：该群已失效或当前群已不存在"
            1100300 -> return "系统内部错误"
            1100301 -> return "调用方不是群成员"
            1100302 -> return "获取指定频道名称失败"
            1100303 -> return "主页频道非管理员不允许发消息"
            1100304 -> return "@次数鉴权失败"
            1100305 -> return "TinyId 转换 Uin 失败"
            1100306 -> return "非私有频道成员"
            1100307 -> return "非白名单应用子频道"
            1100308 -> return "触发频道内限频"
            1100499 -> return "其他错误"
        }
        return null
    }
}
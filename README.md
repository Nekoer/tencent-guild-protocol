# tencent-guild-protocol
Tencent频道的机器人Kotlin SDK


# 导入依赖
#### Maven
```Maven
<dependency>
  <groupId>com.hcyacg</groupId>
  <artifactId>tencent-guild-protocol</artifactId>
  <version>0.1.2</version>
</dependency>
```
#### Gradle Groovy DSL
```Gradle Groovy DSL
implementation 'com.hcyacg:tencent-guild-protocol:0.1.2'
```
#### Gradle Kotlin DSL
```Gradle Kotlin DSL
implementation("com.hcyacg:tencent-guild-protocol:0.1.2")
```
## 如何使用
1. 首先配置你的botId 和 botToken ，这个可以自由设置，我目前是放在idea的`Run/Debug Configation` 的`Program arguments`里了，格式为`id token`
```kotlin
    // 就可以直接从args里面拿到了，如果你觉得麻烦可以用自己的方法实现，能拿到就行，直接写代码里也行
fun main(args: Array<String>) {

    val token = "Bot ${args.first()}.${args.last()}"
    //放入你的Listener
    BotManager(token).addListen(listOf())

}

```
2. 实现你的Listener ，这里会附赠给你一个例子 ：`Test`
```kotlin
    //实际上实现一个Listener十分简单，你只需要继承`BotEvent`，然后实现它里面你需要的方法就可以了
class Test : BotEvent() {

    //这里我实现了 私域中 事件的 监听
    override suspend fun onMessageCreate(data: MessageCreateEvent) {
        
    }
}
```

3.SDK内置了目前官方已出的功能
```kotlin
//获取子频道消息
BotApi.getChannelInfo(data.channel_id)
//获取频道下的子频道列表
BotApi.getChildChannelInfo(data.guild_id)
//获取单个成员消息
BotApi.getMemberInfo(data.guild_id,data.author.id)
//获取频道信息
BotApi.getGuildById(data.guild_id)
//获取频道身份组列表
BotApi.getRolesByGuild(data.guild_id)
//创建频道身份组
BotApi.createRolesByGuild(data.guild_id, Filter(1,1,0),Info("测试",16757760,0))
//修改频道身份组
BotApi.changeRolesByGuild(data.guild_id, "",Filter(1,1,0),Info("测试",16758465,0))
//删除频道身份组
BotApi.deleteRolesByGuild(data.guild_id, "")
//增加频道身份组成员(除子频道管理员
BotApi.createMemberRolesByGuild(data.guild_id, "","")
//增加频道身份组成员(仅子频道管理员
BotApi.createChildMemberRolesByGuild(data.guild_id, BotApi.getChannelInfo(data.channel_id),"","")
//删除频道身份组成员(除子频道管理员
BotApi.deleteMemberRolesByGuild(data.guild_id, "","")
//删除频道身份组成员(仅子频道管理员
BotApi.deleteChildMemberRolesByGuild(data.guild_id, BotApi.getChannelInfo(data.channel_id),"","")
//修改指定子频道的权限 目前只支持修改查看权限
BotApi.changeChannelPermissions(data.channel_id, "",false)
```
4. AtMessageCreateEvent 和 MessageCreateEvent 都存在回复功能
```kotlin
//被动
data.replyArk()
data.replyImage()
data.replyEmbed()
data.replyText()
data.replyAudio()
data.replyTextWithImage()
//主动消息
data.replyArkNotId()
data.replyImageNotId()
data.replyEmbedNotId()
data.replyTextNotId()
data.replyTextWithImageNotId()

//ark模板例子
data.replyArk(
    MessageArk(
        23, listOf(
            MessageArkKv("#DESC#", "descaaaaaa", null),
            MessageArkKv("#PROMPT#", "promptaaaa", null),
            MessageArkKv(
                "#LIST#", null, listOf(
                    MessageArkObj(listOf(MessageArkObjKv("desc", "你好"))),
                    MessageArkObj(listOf(MessageArkObjKv("desc", "你好"))),
                    MessageArkObj(listOf(MessageArkObjKv("desc", "你好"))),
                    MessageArkObj(listOf(MessageArkObjKv("desc", "你好"))),
                    MessageArkObj(listOf(MessageArkObjKv("desc", "你好")))
                )
            )
        )
    )
)
```
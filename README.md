# tencent-guild-protocol
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-2-orange.svg?style=flat-square)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->

> Tencent频道的机器人Kotlin SDK

[![maven-central](https://img.shields.io/maven-central/v/com.hcyacg/tencent-guild-protocol)](https://search.maven.org/artifact/com.hcyacg/tencent-guild-protocol)

## 导入依赖

### Maven

```Maven
<dependency>
  <groupId>com.hcyacg</groupId>
  <artifactId>tencent-guild-protocol</artifactId>
  <version>${version}</version>
</dependency>
```

### Gradle Groovy DSL

```Gradle Groovy DSL
implementation 'com.hcyacg:tencent-guild-protocol:${version}'
```

### Gradle Kotlin DSL

```Gradle Kotlin DSL
implementation("com.hcyacg:tencent-guild-protocol:${version}")
```

## 如何使用

1. 首先配置你的botId 和 botToken

```kotlin
fun main(args: Array<String>) {
    val token = "Bot id.token"
    //放入你的Listener
    //默认是获取公域的信息
    BotManager.configuration(token, false).addListen(Test())
    //私域请使用
    BotManager.configuration(token,true).addListen(Test())
}

```

2. 实现你的Listener ，这里会附赠给你一个例子 ：`Test`

```kotlin
    //实际上实现一个Listener十分简单，你只需要继承`BotEvent`，然后实现它里面你需要的方法就可以了
class Test : BotEvent() {

    //这里我实现了 私域中 事件的 监听
    override suspend fun onMessageCreate(event: MessageCreateEvent) {

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
BotApi.getMemberInfo(data.guild_id, data.author.id)
//获取频道信息
BotApi.getGuildById(data.guild_id)
//获取频道身份组列表
BotApi.getRolesByGuild(data.guild_id)
//创建频道身份组
BotApi.createRolesByGuild(data.guild_id, Filter(1, 1, 0), Info("测试", 16757760, 0))
//修改频道身份组
BotApi.changeRolesByGuild(data.guild_id, "", Filter(1, 1, 0), Info("测试", 16758465, 0))
//删除频道身份组
BotApi.deleteRolesByGuild(data.guild_id, "")
//增加频道身份组成员(除子频道管理员
BotApi.createMemberRolesByGuild(data.guild_id, "", "")
//增加频道身份组成员(仅子频道管理员
BotApi.createChildMemberRolesByGuild(data.guild_id, BotApi.getChannelInfo(data.channel_id), "", "")
//删除频道身份组成员(除子频道管理员
BotApi.deleteMemberRolesByGuild(data.guild_id, "", "")
//删除频道身份组成员(仅子频道管理员
BotApi.deleteChildMemberRolesByGuild(data.guild_id, BotApi.getChannelInfo(data.channel_id), "", "")
//修改指定子频道的权限 目前只支持修改查看权限
BotApi.changeChannelPermissions(data.channel_id, "", false)
//创建子频道公告
BotApi.createAnnounces(data.channel_id, data.id)
//删除子频道公告
BotApi.deleteAnnounces(data.channel_id, message_id)
//获得机器人信息
BotApi.getMe()
//获取当前用户频道列表
BotApi.getMeGuildsAfter(data.guild_id, 100)
BotApi.getMeGuildsBefore(data.guild_id, 100)

//获取日程列表
BotApi.getScheduleList(日程子频道id)
//创建日程
BotApi.createSchedule(日程子频道id, 日程对象)
//修改日程
BotApi.changeScheduleById(日程子频道id, 日程id, 日程对象)
//根据日程id删除日程
BotApi.deleteScheduleById(日程子频道id, 日程id)
//返回结束时间在时间戳之后的日程列表
BotApi.getScheduleListByTime(日程子频道id, 时间戳)

```

### 私域功能

```Kotlin
//获取频道成员列表
BotApi.getGuildMemberList(data.guild_id, "0", 1)
//创建子频道
BotApi.createChannel(data.guild_id, ChannelDto("测试", ChannelType.textSubchannel, 排序id, "父类节点"))
//修改子频道信息
BotApi.changeChannelInfo(data.channel_id, ChannelDto("测试", ChannelType.textSubchannel, 排序id, "父类节点"))
//删除子频道
BotApi.deleteChannel(data.channel_id)
//删除指定频道成员
BotApi.deleteMember(data.guild_id, "用户id")
```

4. AtMessageCreateEvent 和 MessageCreateEvent 都存在回复功能

```kotlin
//被动
event.replyArk()
event.replyImage()
event.replyEmbed()
event.replyText()
event.replyAudio()
event.replyTextWithImage()
//主动消息
event.replyArkNotId()
event.replyImageNotId()
event.replyEmbedNotId()
event.replyTextNotId()
event.replyTextWithImageNotId()
//禁言
event.mute(120)
event.author.mute(120)

//ark模板例子
event.replyArk(
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
## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://www.hcyacg.com/"><img src="https://avatars.githubusercontent.com/u/32485369?v=4?s=100" width="100px;" alt=""/><br /><sub><b>牧瀬くりす</b></sub></a><br /><a href="https://github.com/Nekoer/tencent-guild-protocol/commits?author=Nekoer" title="Code">💻</a></td>
    <td align="center"><a href="https://github.com/cssxsh"><img src="https://avatars.githubusercontent.com/u/32539286?v=4?s=100" width="100px;" alt=""/><br /><sub><b>cssxsh</b></sub></a><br /><a href="https://github.com/Nekoer/tencent-guild-protocol/pulls?q=is%3Apr+reviewed-by%3Acssxsh" title="Reviewed Pull Requests">👀</a></td>
  </tr>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!
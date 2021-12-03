# tencent-guild-protocol
Tencent频道的机器人Kotlin SDK


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

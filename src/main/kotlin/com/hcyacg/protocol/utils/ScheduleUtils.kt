package com.hcyacg.protocol.utils

import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext


object ScheduleUtils : CoroutineScope {
    fun loopEvent(process: suspend () -> Unit, start: Date, period: Long): Timer {
        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                launch {
                    process()
                }
            }
        }
        timer.schedule(task, start, period)
        return timer
    }

    override val coroutineContext: CoroutineContext =
        Dispatchers.IO + CoroutineName(this::class.simpleName!!) + SupervisorJob()
}
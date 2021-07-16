package com.example.demo

import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import java.lang.IllegalArgumentException

fun main() {
    val config = BotConfiguration(
        System.getenv("BOT_NAME") ?: throw IllegalArgumentException("Env variable BOT_NAME is not set"),
        System.getenv("BOT_TOKEN") ?: throw IllegalArgumentException("Env variable BOT_TOKEN is not set"),
    )

    try {
        val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
        botsApi.registerBot(DevmarkBot(config))
    } catch (e: TelegramApiException) {
        e.printStackTrace();
    }
}

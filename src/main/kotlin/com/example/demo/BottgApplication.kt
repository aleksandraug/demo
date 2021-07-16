package com.example.demo

import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import java.lang.IllegalArgumentException

fun getEnvVariable(name: String) =
    System.getenv(name) ?: throw IllegalArgumentException("Env variable $name is not set")


fun main() {
    val config = BotConfiguration(
        name = getEnvVariable("BOT_NAME"),
        token = getEnvVariable("BOT_TOKEN")
    )

    TelegramBotsApi(DefaultBotSession::class.java)
        .registerBot(DevmarkBot(config))
}

package com.example.demo

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

data class BotConfiguration(
    val name: String,
    val token: String,
)

class DevmarkBot(val config: BotConfiguration) : TelegramLongPollingBot() {
    override fun getBotUsername() = config.name

    override fun getBotToken() = config.token

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage()) {
            val message = update.message
            val chatId = message.chatId
            val responseText = if (message.hasText()) {
                val messageText = message.text
                when {
                    messageText == "/start" -> "Здравствуйте! \uD83D\uDC4B\n" +
                            "Я буду уведомлять вас об изменении цен на бирже СПбМТСБ."
                    messageText.startsWith("Кнопка ") -> "Вы нажали кнопку"
                    else -> "Вы написали: *$messageText*"
                }
            } else {
                "Я понимаю только текст"
            }
            sendNotification(chatId, responseText)
        }
    }

    private fun sendNotification(chatId: Long, responseText: String) {
        val responseMessage = SendMessage(chatId.toString(), responseText)
        responseMessage.setParseMode("Markdown")
        responseMessage.replyMarkup = getReplyMarkup(
            listOf(
                listOf("Кнопка 1", "Кнопка 2"),
                listOf("Кнопка 3", "Кнопка 4")
            )
        )
        execute(responseMessage)
    }

    private fun getReplyMarkup(allButtons: List<List<String>>): ReplyKeyboardMarkup {
        val markup = ReplyKeyboardMarkup()
        markup.keyboard = allButtons.map { rowButtons ->
            val row = KeyboardRow()
            rowButtons.forEach { rowButton -> row.add(rowButton) }
            row
        }
        return markup
    }
}
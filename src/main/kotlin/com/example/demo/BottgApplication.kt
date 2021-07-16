package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.telegram.telegrambots.ApiContextInitializer


@SpringBootApplication
class BottgApplication

fun main(args: Array<String>) {
	ApiContextInitializer.init()
	runApplication<BottgApplication>(*args)
}

/*@Service
class TestBot : TelegramLongPollingBot() {

	@Value("\${telegram.botName}")
	private val botName: String = "UGolSashaTaskBot"

	@Value("\${telegram.token}")
	private val token: String = "1898533219:AAGHxHrdMfldUtgQzrr2mOMoFIGaAYRNRG"

	override fun getBotUsername(): String = botName

	override fun getBotToken(): String = token

	override fun onUpdateReceived(update: Update) {
		if (update.hasMessage()) {
			val message = update.message
			val chatId = message.chatId
			val responseText = if (message.hasText()) {
				val messageText = message.text
				when {
					messageText == "/start" -> "Добро пожаловать!"
					else -> "Вы написали: *$messageText*"
				}
			} else {
				"Я понимаю только текст"
			}
			sendNotification(chatId, responseText)
		}
	}
	private fun sendNotification(chatId: Long, responseText: String) {
		val responseMessage = SendMessage(chatId, responseText)
		responseMessage.setParseMode("Markdown")
		execute(responseMessage)
	}

}*/
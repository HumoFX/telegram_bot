package Chat_bot

import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.io.FileReader
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.stream.Collectors


fun main(args: Array<String>) {


    ApiContextInitializer.init()
    try {
        val botsApi = TelegramBotsApi()
        botsApi.registerBot(Bot())
    } catch (e: TelegramApiException) {
        e.printStackTrace()
    }
    println("LoggingTestBot successfully started!")
}


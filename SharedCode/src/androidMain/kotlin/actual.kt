package fr.nutriz.gameof2019

actual fun platformName(): String {
    return "Android"
}

actual fun writeLogMessage(message: String, logLevel: LogLevel) {
    when (logLevel) {
        LogLevel.DEBUG -> println("DEBUG: $message")
        LogLevel.WARN  -> println("WARN: $message")
        else -> System.err.println("ERROR: $message")
    }
}
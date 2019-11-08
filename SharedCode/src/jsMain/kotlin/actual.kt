package fr.nutriz.gameof2019


actual fun platformName(): String {
    return "JS web"
}

actual fun writeLogMessage(message: String, logLevel: LogLevel) {
    console.log("test")
}
package ir.alirahimi.weatherapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
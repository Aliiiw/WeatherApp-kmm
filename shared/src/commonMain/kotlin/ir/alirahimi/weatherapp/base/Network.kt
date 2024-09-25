package ir.alirahimi.weatherapp.base

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object Network {

    @OptIn(ExperimentalSerializationApi::class)
    val httpClient = HttpClient {
        expectSuccess = true
        defaultRequest {
            url("https://api.openweathermap.org/data/2.5/")
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.d(message)
                }
            }
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
                coerceInputValues = true
            })
        }
    }
}
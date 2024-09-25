package ir.alirahimi.weatherapp.repository

import io.ktor.client.call.body
import io.ktor.client.request.get
import ir.alirahimi.weatherapp.base.Network
import ir.alirahimi.weatherapp.repository.dto.CurrentWeatherDTO
import ir.alirahimi.weatherapp.repository.dto.ForecastDTO

class WeatherRepository : IWeatherRepository {

    private val httpClient = Network.httpClient

    override suspend fun getCurrentWeather(): Result<CurrentWeatherDTO> {
        return try {
            val response = httpClient.get("weather").body<CurrentWeatherDTO>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getForecasts(): Result<ForecastDTO> {
        return try {
            val response = httpClient.get("forecast").body<ForecastDTO>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
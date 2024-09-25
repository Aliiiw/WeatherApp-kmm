package ir.alirahimi.weatherapp.repository

import ir.alirahimi.weatherapp.repository.dto.CurrentWeatherDTO
import ir.alirahimi.weatherapp.repository.dto.ForecastDTO

interface IWeatherRepository {
    suspend fun getCurrentWeather(): Result<CurrentWeatherDTO>
    suspend fun getForecasts(): Result<ForecastDTO>
}
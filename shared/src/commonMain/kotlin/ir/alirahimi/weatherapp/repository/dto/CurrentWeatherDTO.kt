package ir.alirahimi.weatherapp.repository.dto

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDTO(
    val name: String,
    val main: MainDTO,
    val weather: List<WeatherDTO>,
    val wind: WindDTO,
)
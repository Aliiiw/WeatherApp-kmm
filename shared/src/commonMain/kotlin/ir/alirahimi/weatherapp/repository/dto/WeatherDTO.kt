package ir.alirahimi.weatherapp.repository.dto

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDTO(
    val main: String,
    val icon: String
)
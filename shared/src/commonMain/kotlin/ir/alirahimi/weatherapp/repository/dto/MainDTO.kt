package ir.alirahimi.weatherapp.repository.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainDTO(
    val temp: Float,
    @SerialName("temp_min")
    val minTemp: Float,
    @SerialName("temp_max")
    val maxTemp: Float,
    val pressure: Int,
    val humidity: Int
)
package ir.alirahimi.weatherapp.repository.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDTO(
    @SerialName("list")
    val forecasts: List<ForecastItemDTO>
) {
    @Serializable
    data class ForecastItemDTO(
        @SerialName("dt_txt")
        val date: String,
        val main: MainDTO,
        val weather: List<WeatherDTO>
    )
}
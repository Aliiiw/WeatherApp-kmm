package ir.alirahimi.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.alirahimi.weatherapp.repository.dto.CurrentWeatherDTO
import ir.alirahimi.weatherapp.repository.dto.ForecastDTO
import ir.alirahimi.weatherapp.usecase.GetWeatherForecastsUseCase
import ir.alirahimi.weatherapp.viewmodel.model.WeatherModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ForecastsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    private val useCase = GetWeatherForecastsUseCase()

    init {
        getWeatherForecasts()
    }

    fun getWeatherForecasts() {
        _uiState.update { UIState(loading = true) }
        viewModelScope.launch {
            useCase().fold(
                onSuccess = { result ->
                    _uiState.update { UIState(items = result.toModel()) }
                },
                onFailure = { throwable ->
                    _uiState.update { UIState(failed = throwable) }
                }
            )
        }
    }

    private fun Pair<CurrentWeatherDTO, ForecastDTO>.toModel(): List<WeatherModel> {
        val currentWeatherDTO = this.first
        val forecastDTO = this.second

        return mutableListOf<WeatherModel>().apply {
            add(
                WeatherModel.CurrentWeatherModel(
                    name = currentWeatherDTO.name,
                    icon = "https://openweathermap.org/img/wn/" + currentWeatherDTO.weather.getOrNull(
                        0
                    )?.icon + "@4x.png",
                    minMaxTemp = "${currentWeatherDTO.main.minTemp}°/${currentWeatherDTO.main.maxTemp}°",
                    temp = "${currentWeatherDTO.main.temp}°",
                    pressure = currentWeatherDTO.main.pressure.toString(),
                    humidity = currentWeatherDTO.main.humidity.toString(),
                    windSpeed = currentWeatherDTO.wind.speed.toString()
                )
            )
            add(WeatherModel.Divider)
            addAll(
                forecastDTO.forecasts.map { forecast ->
                    WeatherModel.ForecastModel(
                        icon = "https://openweathermap.org/img/wn/" + forecast.weather.getOrNull(0)?.icon + "@4x.png",
                        main = forecast.weather.getOrNull(0)?.main ?: "",
                        date = forecast.date,
                        minMaxTemp = "${forecast.main.minTemp.toInt()}°/${forecast.main.maxTemp.toInt()}°"
                    )
                }
            )
        }
    }

}

data class UIState(
    val items: List<WeatherModel> = listOf(),
    val loading: Boolean = false,
    val failed: Throwable? = null
)
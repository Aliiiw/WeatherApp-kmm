package ir.alirahimi.weatherapp.usecase

import ir.alirahimi.weatherapp.repository.WeatherRepository
import ir.alirahimi.weatherapp.repository.dto.CurrentWeatherDTO
import ir.alirahimi.weatherapp.repository.dto.ForecastDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class GetWeatherForecastsUseCase {

    private val repository = WeatherRepository()

    suspend operator fun invoke(dispatcher: CoroutineDispatcher = Dispatchers.IO): Result<Pair<CurrentWeatherDTO, ForecastDTO>> {
        return coroutineScope {
            try {
                withContext(dispatcher) {
                    val currentWeatherAsync = async { repository.getCurrentWeather() }
                    val forecastsAsync = async { repository.getForecasts() }
                    Result.success(
                        Pair(
                            currentWeatherAsync.await().getOrThrow(),
                            forecastsAsync.await().getOrThrow()
                        )
                    )
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
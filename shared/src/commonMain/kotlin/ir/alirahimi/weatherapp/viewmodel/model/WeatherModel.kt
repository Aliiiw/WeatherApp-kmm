package ir.alirahimi.weatherapp.viewmodel.model

sealed class WeatherModel {
    data class CurrentWeatherModel(
        val name: String,
        val icon: String,
        val minMaxTemp: String,
        val temp: String,
        val pressure: String,
        val humidity: String,
        val windSpeed: String
    ) : WeatherModel()

    data object Divider : WeatherModel()

    data class ForecastModel(
        val icon: String,
        val main: String,
        val date: String,
        val minMaxTemp: String
    ) : WeatherModel()
}
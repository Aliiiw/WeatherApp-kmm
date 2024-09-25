package ir.alirahimi.weatherapp.android

import android.app.Application
import ir.alirahimi.weatherapp.initializeNapier

class WeatherApplication:Application( ) {

    override fun onCreate() {
        super.onCreate()
        initializeNapier()
    }
}
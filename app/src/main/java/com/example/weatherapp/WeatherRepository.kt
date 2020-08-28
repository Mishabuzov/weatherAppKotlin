package com.example.weatherapp

import com.example.weatherapp.api.ApiFactory
import com.example.weatherapp.content.CurrentWeather
import com.example.weatherapp.content.WeeklyWeather
import com.example.weatherapp.extensions.extractWeeklyWeather
import com.example.weatherapp.extensions.extractCurrentWeather
import io.reactivex.rxjava3.core.Single

object WeatherProvider {
    val weatherRepository: WeatherRepository = DefaultWeatherRepository()
}

private class DefaultWeatherRepository : WeatherRepository {

    override fun getWeatherFor10Days(cityId: Long): Single<List<WeeklyWeather>> =
        ApiFactory.weatherService.getWeatherForSeveralDays(cityId, Config.CNT)
            .extractWeeklyWeather()

    override fun getCurrentWeather(cityIds: String): Single<List<CurrentWeather>> =
        ApiFactory.weatherService.getCurrentWeatherForCities(cityIds).extractCurrentWeather()
}

interface WeatherRepository {

    fun getWeatherFor10Days(cityId: Long): Single<List<WeeklyWeather>>

    fun getCurrentWeather(cityIds: String): Single<List<CurrentWeather>>

}

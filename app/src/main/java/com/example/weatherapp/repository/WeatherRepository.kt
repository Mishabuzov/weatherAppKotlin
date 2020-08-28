package com.example.weatherapp.repository

import com.example.weatherapp.api.ApiFactory
import com.example.weatherapp.content.DailyWeather
import com.example.weatherapp.content.WeeklyWeatherWrapper
import com.example.weatherapp.utils.Config
import com.example.weatherapp.extensions.convertToDailyWeather
import io.reactivex.rxjava3.core.Single

object WeatherProvider {
    val weatherRepository: WeatherRepository = DefaultWeatherRepository()
}

private class DefaultWeatherRepository : WeatherRepository {

    override fun getWeatherFor10Days(cityName: String): Single<WeeklyWeatherWrapper> =
        ApiFactory.weatherService.getWeatherForSeveralDays(cityName, Config.CNT)

    override fun getCurrentWeather(cityIds: String): Single<List<DailyWeather>> =
        ApiFactory.weatherService.getCurrentWeatherForCities(cityIds).convertToDailyWeather()
}

interface WeatherRepository {

    fun getWeatherFor10Days(cityName: String): Single<WeeklyWeatherWrapper>

    fun getCurrentWeather(cityIds: String): Single<List<DailyWeather>>

}

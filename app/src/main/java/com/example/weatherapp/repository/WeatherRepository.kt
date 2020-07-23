package com.example.weatherapp.repository

import com.example.weatherapp.Config
import com.example.weatherapp.api.ApiFactory
import com.example.weatherapp.content.DailyWeatherWrapper
import com.example.weatherapp.content.WeeklyWeatherWrapper
import io.reactivex.Single

class DefaultWeatherRepository: WeatherRepository {

    override fun getWeatherFor10Days(cityName: String): Single<WeeklyWeatherWrapper> =
        ApiFactory.weatherService.getWeatherForSeveralDays(cityName, Config.CNT)

    override fun getCurrentWeather(cityIds: List<Long>): Single<List<DailyWeatherWrapper>> =
        ApiFactory.weatherService.getCurrentWeatherForCities(cityIds)

}

private interface WeatherRepository {

    fun getWeatherFor10Days(cityName: String): Single<WeeklyWeatherWrapper>

    fun getCurrentWeather(cityIds: List<Long>): Single<List<DailyWeatherWrapper>>

}

package com.example.weatherapp.extensions

import com.example.weatherapp.content.DailyWeather
import com.example.weatherapp.content.DailyWeatherWrapper
import io.reactivex.rxjava3.core.Single

fun Single<DailyWeatherWrapper>.convertToDailyWeather(): Single<List<DailyWeather>> =
    map { weatherWrapper ->
        val dailyWeatherList: MutableList<DailyWeather> = mutableListOf()
        weatherWrapper.dailyWeatherNetworkData.forEach {
            dailyWeatherList.add(
                DailyWeather(
                    it.cityName,
                    it.dailyTemperatureData.temp.toInt(),
                    it.weatherDescriptionList[0].main,
                    it.weatherDescriptionList[0].icon.iconResource
                )
            )
        }
        dailyWeatherList
    }

package com.example.weatherapp.extensions

import com.example.weatherapp.content.CurrentWeather
import com.example.weatherapp.content.CurrentWeatherNetworkData
import com.example.weatherapp.content.CurrentWeatherWrapper

fun CurrentWeatherNetworkData.toCurrentWeather(): CurrentWeather =
    CurrentWeather(
        cityId,
        cityName,
        currentTemperatureData.temp.toInt(),
        weatherDescriptionList[0].main,
        weatherDescriptionList[0].icon.iconResource
    )

fun CurrentWeatherWrapper.toCurrentWeatherList(): List<CurrentWeather> {
    val currentWeatherList: MutableList<CurrentWeather> = mutableListOf()
    currentWeatherNetworkData.forEach {
        currentWeatherList.add(it.toCurrentWeather())
    }
    return currentWeatherList
}

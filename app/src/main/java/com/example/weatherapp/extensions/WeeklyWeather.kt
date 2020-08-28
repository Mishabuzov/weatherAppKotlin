package com.example.weatherapp.extensions

import com.example.weatherapp.content.WeeklyWeather
import com.example.weatherapp.content.WeeklyWeatherNetworkData
import com.example.weatherapp.content.WeeklyWeatherWrapper
import java.text.SimpleDateFormat
import java.util.*

fun WeeklyWeatherNetworkData.toWeeklyWeather() =
    WeeklyWeather(
        temperature.day.toInt(),
        weatherDescriptionList[0].description,
        weatherDescriptionList[0].icon.iconResource,
        getHumanReadableDate(date),
        convertPressure(pressure),
        humidity,
        speed
    )

private fun getHumanReadableDate(dateInSeconds: Long): String {
    val dateInMillisecond = Date(dateInSeconds * 1000)
    val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("GMT-4")
    return sdf.format(dateInMillisecond)
}

private fun convertPressure(hpaValue: Double): Int = (hpaValue * 0.75).toInt()

fun WeeklyWeatherWrapper.toWeeklyWeatherList(): List<WeeklyWeather> {
    val weeklyWeatherList: MutableList<WeeklyWeather> = mutableListOf()
    weeklyWeeklyWeatherNetworkList.forEach {
        weeklyWeatherList.add(it.toWeeklyWeather())
    }
    return weeklyWeatherList
}

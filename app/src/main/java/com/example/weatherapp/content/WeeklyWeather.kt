package com.example.weatherapp.content

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName


data class WeeklyWeatherWrapper(
    @SerializedName("list") val weeklyWeeklyWeatherNetworkList: List<WeeklyWeatherNetworkData>,
    val city: City
)

data class City(val name: String)

data class WeeklyWeatherNetworkData(
    @SerializedName("dt") val date: Long,
    @SerializedName("temp") val temperature: Temperature,
    @SerializedName("weather") val weatherDescriptionList: List<WeatherDescription>,
    val pressure: Double,
    val humidity: Int,
    val speed: Double
)

data class Temperature(val day: Double, val min: Double, val max: Double)

data class WeeklyWeather(
    val temperature: Int,
    val weatherDescription: String,
    @DrawableRes val weatherIconResource: Int,
    val humanReadableDate: String,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double
)



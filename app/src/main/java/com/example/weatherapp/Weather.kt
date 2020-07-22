package com.example.weatherapp

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class Weather(val main: String, val description: String)

data class CurrentWeather(val temp: Double)

data class CurrentWeatherInfo(
    @SerializedName("main") val currentWeather: CurrentWeather,
    val name: String
)

data class FullWeatherInfo(
    @SerializedName("list") val weatherInfos: List<WeatherInfo>,
    val city: City
)

data class Temperature(val day: Double, val min: Double, val max: Double)

data class City(val name: String)

data class WeatherInfo(
    @SerializedName("dt") val date: Long,
    @SerializedName("temp") val temperature: Temperature,
    val weather: List<Weather>,
    val pressure: Double,
    val humidity: Int,
    val speed: Double
) {
    fun getHumanReadableDate(): String {
        val date = Date(date * 1000)
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        sdf.timeZone = TimeZone.getTimeZone("GMT-4")
        return sdf.format(date)
    }
}

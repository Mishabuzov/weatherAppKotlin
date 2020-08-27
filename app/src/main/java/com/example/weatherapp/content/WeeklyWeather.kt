package com.example.weatherapp.content

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*


data class WeeklyWeatherWrapper(
    @SerializedName("list") val weeklyWeatherList: List<WeatherData>,
    val city: City
)

data class City(val name: String)

data class WeatherData(
    @SerializedName("dt") val date: Long,
    @SerializedName("temp") val temperature: Temperature,
    @SerializedName("weather") val weatherDescriptionList: List<WeatherDescription>,
    val pressure: Double,
    val humidity: Int,
    val speed: Double
) {
    fun getHumanReadableDate(): String {
        val date = Date(date * 1000)
        val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("GMT-4")
        return sdf.format(date)
    }
}

data class Temperature(val day: Double, val min: Double, val max: Double)

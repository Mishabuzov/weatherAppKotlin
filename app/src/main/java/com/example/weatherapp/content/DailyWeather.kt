package com.example.weatherapp.content

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

data class DailyWeatherWrapper(
    @SerializedName("list") val dailyWeatherNetworkData: List<DailyWeatherNetworkData>
)

data class DailyWeatherNetworkData(
    @SerializedName("main") val dailyTemperatureData: DailyTemperatureData,
    @SerializedName("name") val cityName: String,
    @SerializedName("weather") val weatherDescriptionList: List<WeatherDescription>
)

data class DailyTemperatureData(val temp: Double)

data class DailyWeather(
    val cityName: String,
    val temperature: Int,
    val weatherDescription: String,
    @DrawableRes val weatherIconResource: Int
)

package com.example.weatherapp.content

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

data class CurrentWeatherWrapper(
    @SerializedName("list") val currentWeatherNetworkData: List<CurrentWeatherNetworkData>
)

data class CurrentWeatherNetworkData(
    @SerializedName("main") val currentTemperatureData: CurrentTemperatureData,
    @SerializedName("name") val cityName: String,
    @SerializedName("weather") val weatherDescriptionList: List<WeatherDescription>
)

data class CurrentTemperatureData(val temp: Double)

data class CurrentWeather(
    val cityName: String,
    val temperature: Int,
    val weatherDescription: String,
    @DrawableRes val weatherIconResource: Int
)

package com.example.weatherapp.content

import com.google.gson.annotations.SerializedName

data class DailyWeatherWrapper(
    @SerializedName("main") val dailyWeatherData: DailyWeatherData,
    @SerializedName("name") val cityName: String
)

data class DailyWeatherData(val temp: Double)

//data class CurrentCityWeather(val cityName: String, val currentTemp: Double)

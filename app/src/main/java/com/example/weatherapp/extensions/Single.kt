package com.example.weatherapp.extensions

import com.example.weatherapp.content.CurrentWeather
import com.example.weatherapp.content.CurrentWeatherWrapper
import com.example.weatherapp.content.WeeklyWeather
import com.example.weatherapp.content.WeeklyWeatherWrapper
import io.reactivex.rxjava3.core.Single

fun Single<CurrentWeatherWrapper>.extractCurrentWeather(): Single<List<CurrentWeather>> =
    map(CurrentWeatherWrapper::toCurrentWeatherList)

fun Single<WeeklyWeatherWrapper>.extractWeeklyWeather(): Single<List<WeeklyWeather>> =
    map(WeeklyWeatherWrapper::toWeeklyWeatherList)

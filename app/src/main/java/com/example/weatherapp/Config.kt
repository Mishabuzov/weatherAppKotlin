package com.example.weatherapp

object Config {

    val WEATHER_ENDPOINT_DEBUG = "http://api.openweathermap.org/"
    val WEATHER_ENDPOINT_RELEASE = WEATHER_ENDPOINT_DEBUG

    val APPLICATION_ID = "7d848404f9f02c778b6cdfa0760e8d0d"
    val UNITS = "metric"
    val CNT = 10  // days count for returning value for concrete city.
    val LANG = "ru"

    val citiesIds = mapOf<String, Long>(
        "Moscow" to 524901,
        "Kyiv" to 703448,
        "London" to 2643743,
        "Kazan" to 551487,
        "Tokyo" to 1850144,
        "Amsterdam" to 2759794
    )

}
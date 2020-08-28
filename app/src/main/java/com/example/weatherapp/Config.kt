package com.example.weatherapp

object Config {

    val WEATHER_ENDPOINT_DEBUG = "https://api.openweathermap.org/"
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
        "Amsterdam" to 2759794,
        "Beijing" to 1816670,
        "New York" to 5128581,
        "Astana" to 1526273,
        "Washington" to 5815135,
        "Toronto" to 6167865,
        "Monreal" to 1699310,
        "Paris" to 2988507,
        "Berlin" to 2950159,
        "Saint Petersburg" to 498817,
        "Magadan" to 2123628,
        "Warsaw" to 756135,
        "Viena" to 2761369,
        "Marcel" to 2996051,
        "Roma" to 5134295
    )

}
package com.example.weatherapp.api

import com.example.weatherapp.content.CurrentWeatherWrapper
import com.example.weatherapp.content.WeeklyWeatherWrapper
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    /**
     * Returns weather for several days by chosen city.
     */
    @GET("data/2.5/forecast/daily")
    fun getWeatherForSeveralDays(
        @Query("id") cityId: Long,
        @Query("cnt") daysCount: Int
    ): Single<WeeklyWeatherWrapper>


    /**
     * Obtains current weather for chosen cities by provided ids.
     * @param: cityIds - list of unique identifiers of defined cities.
     */
    @GET("data/2.5/group")
    fun getCurrentWeatherForCities(
        @Query("id") cityIds: String
    ): Single<CurrentWeatherWrapper>

}
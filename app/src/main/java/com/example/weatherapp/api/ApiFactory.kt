package com.example.weatherapp.api

import com.example.weatherapp.Config
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    val weatherService: WeatherService = run {
        buildRetrofit().create(WeatherService::class.java)
    }

    private fun buildRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Config.WEATHER_ENDPOINT_DEBUG)
            .client(buildClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //rx wrapper
            .build()

    private fun buildClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor.create())
        httpClient.networkInterceptors().add(StethoInterceptor())
        return httpClient.build()
    }

}
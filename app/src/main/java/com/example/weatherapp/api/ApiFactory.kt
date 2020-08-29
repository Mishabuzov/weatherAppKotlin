package com.example.weatherapp.api

import com.example.weatherapp.Config
import com.facebook.stetho.okhttp3.StethoInterceptor
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    val weatherService: WeatherService by lazy { buildRetrofit().create(WeatherService::class.java) }

    // HttpLoggingInterceptor prints details about Http-request into the logs.
    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    private fun buildRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Config.WEATHER_ENDPOINT_DEBUG)
        .client(buildClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //rx wrapper
        .build()

    private fun buildClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor)
        .addInterceptor(loggingInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .build()

}
package com.example.weatherapp.api

import com.example.weatherapp.Config
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    companion object Initializer {
        fun create(): Interceptor {
            return ApiKeyInterceptor()
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()

        val url: HttpUrl = originalRequest.url.newBuilder()
            .addQueryParameter("appid", Config.APPLICATION_ID)
            .addQueryParameter("units", Config.UNITS)
            .addQueryParameter("lang", Config.LANG)
            .build()

        // Request customization: add request headers
        val request: Request = originalRequest
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}
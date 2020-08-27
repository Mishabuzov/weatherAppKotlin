package com.example.weatherapp.content

import com.example.weatherapp.R
import com.google.gson.annotations.SerializedName

data class WeatherDescription(val main: String, val description: String, val icon: IconType)

enum class IconType(val iconResource: Int) {
    @SerializedName("01d") DAY_1(R.drawable.d01),
    @SerializedName("02d") DAY_2(R.drawable.d02),
    @SerializedName("03d") DAY_3(R.drawable.d03),
    @SerializedName("04d") DAY_4(R.drawable.d04),
    @SerializedName("09d") DAY_9(R.drawable.d09),
    @SerializedName("10d") DAY_10(R.drawable.d10),
    @SerializedName("11d") DAY_11(R.drawable.d11),
    @SerializedName("13d") DAY_13(R.drawable.d13),
    @SerializedName("50d") DAY_50(R.drawable.d50),
    @SerializedName("01n") NIGHT_1(R.drawable.n01),
    @SerializedName("02n") NIGHT_2(R.drawable.n02),
    @SerializedName("03n") NIGHT_3(R.drawable.d03),
    @SerializedName("04n") NIGHT_4(R.drawable.d04),
    @SerializedName("09n") NIGHT_9(R.drawable.d09),
    @SerializedName("10n") NIGHT_10(R.drawable.n10),
    @SerializedName("11n") NIGHT_11(R.drawable.d11),
    @SerializedName("13n") NIGHT_13(R.drawable.d13),
    @SerializedName("50n") NIGHT_50(R.drawable.d50)
}

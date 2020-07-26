package com.example.weatherapp.screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.content.DailyWeather

class CityViewModel : ViewModel() {

    val cityWeatherList: MutableLiveData<List<DailyWeather>> = MutableLiveData()

    fun getCityWeather(): MutableLiveData<List<DailyWeather>> = cityWeatherList

    fun updateCityWeather(cityWeatherList: List<DailyWeather>) {
        this.cityWeatherList.value = cityWeatherList
    }

}
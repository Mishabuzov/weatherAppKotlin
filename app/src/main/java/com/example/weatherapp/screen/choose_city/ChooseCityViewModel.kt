package com.example.weatherapp.screen.choose_city

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.Config
import com.example.weatherapp.WeatherProvider
import com.example.weatherapp.content.CurrentWeather
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ChooseCityViewModel() : ViewModel() {

    private val currentWeatherList: MutableLiveData<List<CurrentWeather>> = MutableLiveData()

    fun getCityWeather(): MutableLiveData<List<CurrentWeather>> = currentWeatherList

    lateinit var refreshDataCallback: RefreshDataCallback

    fun processCurrentWeatherRequest() =
        WeatherProvider.weatherRepository
            .getCurrentWeather(Config.citiesIds.values.joinToString(separator = ","))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    currentWeatherList.value = it
                    refreshDataCallback.refreshAdapter(it)
                },
                {
                    Log.d(
                        "M_ChooseCityViewModel",
                        "Error getting current weather list\n${it.message}"
                    )
                }
            )

    interface RefreshDataCallback {
        fun refreshAdapter(currentWeathers: List<CurrentWeather>)
    }

}
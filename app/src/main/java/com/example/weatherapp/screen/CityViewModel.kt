package com.example.weatherapp.screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.Config
import com.example.weatherapp.content.DailyWeather
import com.example.weatherapp.repository.WeatherProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CityViewModel(private val citiesCallback: CitiesCallback) : ViewModel() {

    private val cityWeatherList: MutableLiveData<List<DailyWeather>> = MutableLiveData()

    fun getCityWeather(): MutableLiveData<List<DailyWeather>> = cityWeatherList

    fun processWeatherRequest() {
        WeatherProvider.weatherRepository
            .getCurrentWeather(Config.citiesIds.values.joinToString(separator = ","))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    cityWeatherList.value = it.dailyWeather
                    citiesCallback.refreshAdapter(it.dailyWeather)
                },
                {
                    Log.d(
                        "M_CityViewModel",
                        "Error getting city weather list\n${it.stackTrace}"
                    )
                }
            )
    }

    class CityViewModelFactory(private val citiesCallback: CitiesCallback) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(CitiesCallback::class.java).newInstance(citiesCallback)
        }
    }

    interface CitiesCallback {
        fun refreshAdapter(cities: List<DailyWeather>)
    }

}
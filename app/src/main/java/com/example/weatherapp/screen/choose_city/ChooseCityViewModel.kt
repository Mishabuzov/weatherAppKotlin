package com.example.weatherapp.screen.choose_city

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.utils.Config
import com.example.weatherapp.content.CurrentWeather
import com.example.weatherapp.repository.WeatherProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ChooseCityViewModel(private val refreshDataCallback: RefreshDataCallback) : ViewModel() {

    private val currentWeatherList: MutableLiveData<List<CurrentWeather>> = MutableLiveData()

    fun getCityWeather(): MutableLiveData<List<CurrentWeather>> = currentWeatherList

    fun processWeatherRequest() {
        WeatherProvider.weatherRepository
            .getCurrentWeather(Config.citiesIds.values.joinToString(separator = ","))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    currentWeatherList.value = it
                    refreshDataCallback.refreshAdapter(it)
                },
                {
                    Log.d(
                        "M_CityViewModel",
                        "Error getting city weather list\n${it.message}"
                    )
                }
            )
    }

    class CityViewModelFactory(private val refreshDataCallback: RefreshDataCallback) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(RefreshDataCallback::class.java)
                .newInstance(refreshDataCallback)
        }
    }

    interface RefreshDataCallback {
        fun refreshAdapter(currentWeathers: List<CurrentWeather>)
    }

}
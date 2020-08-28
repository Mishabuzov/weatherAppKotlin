package com.example.weatherapp.screen.city_weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.content.CurrentWeather
import com.example.weatherapp.content.WeeklyWeather
import com.example.weatherapp.repository.WeatherProvider
import com.example.weatherapp.screen.choose_city.ChooseCityViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CityViewModel(private val refreshDataCallback: RefreshDataCallback) : ViewModel() {

    private val weeklyWeatherList: MutableLiveData<List<WeeklyWeather>> = MutableLiveData()

    fun getWeeklyWeather(): MutableLiveData<List<WeeklyWeather>> = weeklyWeatherList

    fun processWeatherRequest(cityName: Long) =
        WeatherProvider.weatherRepository
            .getWeatherFor10Days(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    weeklyWeatherList.value = it
                    refreshDataCallback.refreshAdapter(it)
                },
                {
                    Log.d(
                        "M_CityViewModel",
                        "Error getting weekly weather list\n${it.message}"
                    )
                }
            )

    class CityViewModelFactory(private val refreshDataCallback: RefreshDataCallback) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(RefreshDataCallback::class.java)
                .newInstance(refreshDataCallback)
        }
    }

    interface RefreshDataCallback {
        fun refreshAdapter(weeklyWeathers: List<WeeklyWeather>)
    }

}
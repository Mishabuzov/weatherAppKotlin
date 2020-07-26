package com.example.weatherapp.screen

import android.os.Bundle
import android.util.Log.e
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.Config
import com.example.weatherapp.R
import com.example.weatherapp.repository.WeatherProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_choose_city.*

class ChooseCityActivity : AppCompatActivity() {

    private val weatherViewModel by lazy {
        ViewModelProvider(this)
            .get(CityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_city)

        val adapter = ChooseCityAdapter()
        cityList.layoutManager = LinearLayoutManager(this)
        cityList.adapter = adapter
        weatherViewModel.getCityWeather().observe(this, Observer {
            it?.let {
                adapter.refreshWeather(it)
            }
        })
        processWeatherRequest(adapter)
    }

    fun processWeatherRequest(adapter: ChooseCityAdapter) {
        WeatherProvider.weatherRepository
            .getCurrentWeather(Config.citiesIds.values.joinToString(separator = ","))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    weatherViewModel.updateCityWeather(it.dailyWeather)
                    adapter.refreshWeather(it.dailyWeather)
                },
                {
                    e("REQUEST ERROR", "Error getting city weather list")
                }
            )
    }

}
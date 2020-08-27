package com.example.weatherapp.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.content.DailyWeather
import kotlinx.android.synthetic.main.activity_choose_city.*

class ChooseCityActivity : AppCompatActivity(), CityViewModel.CitiesCallback {

    private val weatherViewModel by lazy {
        ViewModelProvider(this, CityViewModel.CityViewModelFactory(this))
            .get(CityViewModel(this)::class.java)
    }

    private val adapter = ChooseCityAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_city)

        cityList.layoutManager = LinearLayoutManager(this)
        cityList.adapter = adapter
        val currentLiveData = weatherViewModel.getCityWeather()
        currentLiveData.observe(this, Observer {
            it?.let { refreshAdapter(it) }
        })
        if (currentLiveData.value.isNullOrEmpty()) {
            weatherViewModel.processWeatherRequest()
        }
    }

    override fun refreshAdapter(cities: List<DailyWeather>) {
        adapter.refreshWeather(cities)
    }

}
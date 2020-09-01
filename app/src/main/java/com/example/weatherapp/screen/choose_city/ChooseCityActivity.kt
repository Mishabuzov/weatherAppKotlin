package com.example.weatherapp.screen.choose_city

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.content.CurrentWeather
import com.example.weatherapp.screen.DividerItemDecoration
import com.example.weatherapp.screen.city_weather.CityActivity
import kotlinx.android.synthetic.main.activity_choose_city.*

class ChooseCityActivity : AppCompatActivity(), ChooseCityViewModel.RefreshDataCallback {

    private val chooseCityViewModel by lazy {
        ViewModelProvider(this).get(ChooseCityViewModel::class.java)
    }

    private val adapter = ChooseCityAdapter { CityActivity.start(this, it.first, it.second) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_city)

        chooseCityViewModel.refreshDataCallback = this
        initAdapter()

        val currentLiveData = chooseCityViewModel.getCityWeather()
        currentLiveData.observe(this, {
            it?.let { refreshAdapter(it) }
        })
        if (currentLiveData.value.isNullOrEmpty()) {
            chooseCityViewModel.processCurrentWeatherRequest()
        }
    }

    private fun initAdapter() {
        choose_city_list.layoutManager = LinearLayoutManager(this)
        choose_city_list.adapter = this.adapter
        choose_city_list.addItemDecoration(DividerItemDecoration(this))
    }

    override fun refreshAdapter(currentWeathers: List<CurrentWeather>) {
        adapter.refreshWeather(currentWeathers)
    }

}
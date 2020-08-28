package com.example.weatherapp.screen.city_weather

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.content.WeeklyWeather
import kotlinx.android.synthetic.main.activity_city_weather.*

class CityActivity : AppCompatActivity(), CityViewModel.RefreshDataCallback {

    companion object {
        const val CITY_ID_KEY = "city_id"

        fun start(activity: AppCompatActivity, cityName: Long) {
            val intent = Intent(activity, CityActivity::class.java)
            intent.putExtra(CITY_ID_KEY, cityName)
            activity.startActivity(intent)
        }
    }

    private val cityViewModel by lazy {
        ViewModelProvider(this, CityViewModel.CityViewModelFactory(this))
            .get(CityViewModel(this)::class.java)
    }

    private val adapter = CityAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_weather)

        val cityId = intent.getLongExtra(CITY_ID_KEY, 0)
        initAdapter()

        val weeklyLiveData = cityViewModel.getWeeklyWeather()
        weeklyLiveData.observe(this, Observer {
            it?.let { refreshAdapter(it) }
        })
        if (weeklyLiveData.value.isNullOrEmpty()) {
            cityViewModel.processWeatherRequest(cityId)
        }
    }

    private fun initAdapter() {
        weekly_weather_list.layoutManager = LinearLayoutManager(this)
        weekly_weather_list.adapter = this.adapter
    }

    override fun refreshAdapter(weeklyWeathers: List<WeeklyWeather>) {
        adapter.refreshWeather(weeklyWeathers)
    }
}


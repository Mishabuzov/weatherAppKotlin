package com.example.weatherapp.screen.choose_city

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.example.weatherapp.R
import com.example.weatherapp.content.CurrentWeather
import com.example.weatherapp.screen.DividerItemDecoration
import com.example.weatherapp.screen.city_weather.CityActivity
import kotlinx.android.synthetic.main.activity_choose_city.*

class ChooseCityActivity : AppCompatActivity(), ChooseCityViewModel.RefreshDataCallback {

    private val chooseCityViewModel by lazy {
        ViewModelProvider(this, ChooseCityViewModel.ChooseCityViewModelFactory(this))
            .get(ChooseCityViewModel(this)::class.java)
    }

    private val adapter = ChooseCityAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_city)

        initAdapter()

        val currentLiveData = chooseCityViewModel.getCityWeather()
        currentLiveData.observe(this, Observer {
            it?.let { refreshAdapter(it) }
        })
        if (currentLiveData.value.isNullOrEmpty()) {
            chooseCityViewModel.processCurrentWeatherRequest()
        }
    }

    private fun initAdapter() {
        adapter.onItemClick = {
            CityActivity.start(this, it)
        }
        choose_city_list.layoutManager = LinearLayoutManager(this)
        choose_city_list.adapter = this.adapter
        choose_city_list.addItemDecoration(DividerItemDecoration(this))
    }

    override fun refreshAdapter(currentWeathers: List<CurrentWeather>) {
        adapter.refreshWeather(currentWeathers)
    }

}
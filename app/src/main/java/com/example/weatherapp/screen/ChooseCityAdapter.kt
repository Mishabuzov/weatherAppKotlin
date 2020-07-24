package com.example.weatherapp.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.content.DailyWeatherWrapper
import kotlinx.android.synthetic.main.city_list_item.view.*

class ChooseCityAdapter : RecyclerView.Adapter<ChooseCityAdapter.ChooseCityHolder>() {

    private var cities: List<DailyWeatherWrapper> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseCityHolder {
        return ChooseCityHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.city_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: ChooseCityHolder, position: Int) {
        holder.bind(cities[position])
    }

    class ChooseCityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dailyWeather: DailyWeatherWrapper) = with(itemView) {
            cityNameTextView.text = dailyWeather.cityName
            tempTextView.text = dailyWeather.dailyWeatherData.temp.toString()
        }
    }

}
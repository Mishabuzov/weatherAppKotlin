package com.example.weatherapp.screen.choose_city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.content.DailyWeather
import kotlinx.android.synthetic.main.city_list_item.view.*
import kotlin.math.roundToInt

class ChooseCityAdapter : RecyclerView.Adapter<ChooseCityAdapter.ChooseCityHolder>() {

    var cities: List<DailyWeather> = ArrayList()

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

    fun refreshWeather(cities: List<DailyWeather>) {
        this.cities = cities
        notifyDataSetChanged()
    }

    class ChooseCityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dailyWeather: DailyWeather) = with(itemView) {
            city_name_text_view.text = dailyWeather.cityName
            temp_text_view.text = String.format(
                resources.getString(R.string.celsius_format),
                dailyWeather.dailyWeatherData.temp.roundToInt()
            )
            weather_icon.setImageResource(dailyWeather.weatherDescriptionList[0].icon.iconResource)
        }
    }

}
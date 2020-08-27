package com.example.weatherapp.screen.choose_city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.content.DailyWeather
import kotlinx.android.synthetic.main.city_list_item.view.*

class ChooseCityAdapter : RecyclerView.Adapter<ChooseCityAdapter.ChooseCityHolder>() {

    private var dailyWeathers: List<DailyWeather> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseCityHolder {
        return ChooseCityHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.city_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = dailyWeathers.size

    override fun onBindViewHolder(holder: ChooseCityHolder, position: Int) {
        holder.bind(dailyWeathers[position])
    }

    fun refreshWeather(dailyWeathers: List<DailyWeather>) {
        this.dailyWeathers = dailyWeathers
        notifyDataSetChanged()
    }

    class ChooseCityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dailyWeather: DailyWeather) = with(itemView) {
            city_name_text_view.text = dailyWeather.cityName
            temp_text_view.text = String.format(
                resources.getString(R.string.celsius_format),
                dailyWeather.temperature
            )
            weather_icon.setImageResource(dailyWeather.weatherIconResource)
        }
    }

}
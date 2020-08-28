package com.example.weatherapp.screen.city_weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.content.WeeklyWeather
import kotlinx.android.synthetic.main.weather_list_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class CityAdapter : RecyclerView.Adapter<CityAdapter.CityHolder>() {

    private var weeklyWeathers: List<WeeklyWeather> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder =
        CityHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: CityHolder, position: Int) =
        holder.bind(weeklyWeathers[position])

    override fun getItemCount(): Int = weeklyWeathers.size

    fun refreshWeather(weeklyWeathers: List<WeeklyWeather>) {
        this.weeklyWeathers = weeklyWeathers
        notifyDataSetChanged()
    }

    class CityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weeklyWeather: WeeklyWeather) = with(itemView) {
            date_text_view.text = weeklyWeather.humanReadableDate
            description_text_view.text =
                weeklyWeather.weatherDescription.capitalize(Locale.getDefault())
            temp_text_view.text = String.format(
                resources.getString(R.string.celsius_format),
                weeklyWeather.temperature
            )
            pressure_text_view.text = String.format(
                resources.getString(R.string.pressure_format),
                weeklyWeather.pressure
            )
            humidity_text_view.text = String.format(
                resources.getString(R.string.humidity_format),
                weeklyWeather.humidity
            )
            wind_text_view.text = String.format(
                resources.getString(R.string.wind_format),
                weeklyWeather.windSpeed
            )
            weather_icon.setImageResource(weeklyWeather.weatherIconResource)
        }
    }

}
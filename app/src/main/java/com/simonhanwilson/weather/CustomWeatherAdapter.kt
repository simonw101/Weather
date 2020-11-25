package com.simonhanwilson.weather

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.pwittchen.weathericonview.WeatherIconView

class CustomWeatherAdapter(val context: Context, val iconList: ArrayList<String>, val weatherInformationList: ArrayList<String>) : RecyclerView.Adapter<CustomWeatherAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            val list_text = view.findViewById<TextView>(R.id.list_textView)

            val icon_list = view.findViewById<WeatherIconView>(R.id.list_icon)


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
        }

        override fun getItemCount(): Int {
            return weatherInformationList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.list_text.text = weatherInformationList[position]

        }

}
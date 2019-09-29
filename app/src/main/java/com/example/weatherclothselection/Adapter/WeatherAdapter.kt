package com.example.weatherclothselection.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.weatherclothselection.Data.Entry
import com.example.weatherclothselection.R


class WeatherAdapter(val context : Context, val weatherList : List<Entry>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view : View = LayoutInflater.from(context).inflate(R.layout.weather_list_item,null)

        val temp = view.findViewById<TextView>(R.id.temp_list_text_view)
        val wet = view.findViewById<TextView>(R.id.wet_list_text_view)
        val weatherInform = view. findViewById<TextView>(R.id.weather_list_text_view)
        val time = view.findViewById<TextView>(R.id.time_list_text_view)
        val weather = weatherList.get(position)

        temp.text = weather.tm
        wet.text = weather.reh
        weatherInform.text = weather.wfKor
        time.text = weather.pubDate

        return view

    }

    override fun getItem(position: Int): Any {

        return weatherList.get(position)


    }

    override fun getItemId(position: Int): Long {

        return 0

    }

    override fun getCount(): Int {

        return weatherList.size
    }
}

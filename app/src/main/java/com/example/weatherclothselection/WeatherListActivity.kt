package com.example.weatherclothselection

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.example.weatherclothselection.Adapter.WeatherAdapter
import com.example.weatherclothselection.Data.Entry
import com.example.weatherclothselection.Presenter.WeatherListContract

import kotlinx.android.synthetic.main.activity_weather_list.*

class WeatherListActivity : AppCompatActivity(),WeatherListContract.View {

    lateinit override var presenter: WeatherListContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)

    }

    override fun updateWeatherList() {

    }

    override fun notifyAdapter(isChange: Boolean, weatherList: List<Entry>) {

        val weatherAdapter = WeatherAdapter(this, weatherList)
        weather_list_view.adapter = weatherAdapter

    }
}

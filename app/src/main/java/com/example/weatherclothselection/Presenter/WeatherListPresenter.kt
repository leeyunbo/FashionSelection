package com.example.weatherclothselection.Presenter

import com.example.weatherclothselection.Data.Entry
import com.example.weatherclothselection.Data.GetWeatherData
import java.io.InputStream


class WeatherListPresenter : WeatherListContract.Presenter{
    lateinit override var weatherData: GetWeatherData
    lateinit var weatherList : List<Entry>

    override fun getWeatherList(weatherList : List<Entry>, view : WeatherListContract.View) {

        view.notifyAdapter(true,weatherList)

        }
    }


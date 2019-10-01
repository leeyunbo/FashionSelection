package com.example.weatherclothselection.Presenter

import com.example.weatherclothselection.Data.Entry
import com.example.weatherclothselection.Data.GetWeatherData
import java.io.InputStream


class WeatherListPresenter : WeatherListContract.Presenter {
    lateinit override var weatherData: GetWeatherData
    lateinit override var view: WeatherListContract.View

    override fun getWeatherList(): List<Entry> {


        return weatherData.getWeatherData()
    }

}






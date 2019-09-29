package com.example.weatherclothselection.Presenter

import com.example.weatherclothselection.Data.GetWeatherData
import java.io.InputStream


class WeatherListPresenter : WeatherListContract.Presenter{
    lateinit override var view: WeatherListContract.View
    lateinit override var weatherData: GetWeatherData

    override fun requestWeatherList(inputStream: InputStream) {
        weatherData = GetWeatherData()
        weatherData.getWeatherData(inputStream).let {
            view.notifyAdapter(true, it)

        }
    }
}

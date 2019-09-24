package com.example.weatherclothselection.Presenter

import com.example.weatherclothselection.Data.GetWeatherData
import java.io.InputStream

class MainPresenter : MainContract.Presenter {
    lateinit override var view : MainContract.View
    lateinit override var weatherData: GetWeatherData

    override fun getWeatherData(inputStream: InputStream) {
        weatherData.GetWeatherData(inputStream).let {
            view.updateWeatherUI(it)
        }
    }

}
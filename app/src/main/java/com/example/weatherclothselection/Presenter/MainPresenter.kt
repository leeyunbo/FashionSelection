package com.example.weatherclothselection.Presenter

import com.example.weatherclothselection.Data.GetWeatherData
import java.io.InputStream

class MainPresenter : MainContract.Presenter {
    lateinit override var view : MainContract.View
    lateinit override var weatherData: GetWeatherData
    lateinit override var inputStream: InputStream

    override fun getWeatherData() {
        weatherData.GetWeatherData(inputStream).let {
            view.updateWeatherUI(it)
        }
    }

}
package com.example.weatherclothselection.Presenter

import com.example.weatherclothselection.Data.Entry
import com.example.weatherclothselection.Data.GetWeatherData
import java.io.InputStream

interface WeatherListContract {
    interface View {
        var presenter : WeatherListContract.Presenter
        var weather_list : List<Entry>

        fun updateWeatherList(isChange : Boolean, weather_list: List<Entry>) //날씨 관련 UI 업데이트
        fun networkConnect()
        fun loadXmlFromNetwork(urlString : String)
        fun downloadUrl(urlString: String) : InputStream?

    }


    interface Presenter {
        var weatherData : GetWeatherData
        var view : WeatherListContract.View

        fun getWeatherList(inputStream: InputStream) : List<Entry> // 날씨 관련 데이터 요청


    }
}
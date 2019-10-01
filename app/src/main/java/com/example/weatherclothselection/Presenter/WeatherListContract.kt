package com.example.weatherclothselection.Presenter

import com.example.weatherclothselection.Data.Entry
import com.example.weatherclothselection.Data.GetWeatherData
import java.io.InputStream

interface WeatherListContract {
    interface View {
        var presenter : WeatherListContract.Presenter

        fun updateWeatherList(isChange : Boolean, weather_list: List<Entry>) //날씨 관련 UI 업데이트
        fun requestWeatherList() : List<Entry>
        fun networkConnect()

    }


    interface Presenter {
        var weatherData : GetWeatherData
        var view : WeatherListContract.View

        fun getWeatherList() : List<Entry> // 날씨 관련 데이터 요청


    }
}
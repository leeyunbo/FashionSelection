package com.example.weatherclothselection.Presenter

import com.example.weatherclothselection.Data.Entry
import com.example.weatherclothselection.Data.GetWeatherData
import java.io.InputStream

interface WeatherListContract {
    interface View {
        var presenter : WeatherListContract.Presenter

        fun updateWeatherList() //날씨 관련 UI 업데이트
        fun notifyAdapter(isChange : Boolean, weatherList : List<Entry>) //어뎁터 변경 알림


    }


    interface Presenter {
        var view : WeatherListContract.View
        var weatherData : GetWeatherData

        fun requestWeatherList(inputStream: InputStream) // 날씨 관련 데이터 요청


    }
}
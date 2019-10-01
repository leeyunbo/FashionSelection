package com.example.weatherclothselection.Presenter

import com.example.weatherclothselection.Data.Entry
import com.example.weatherclothselection.Data.GetWeatherData
import java.io.InputStream

interface WeatherListContract {
    interface View {
        var presenter : WeatherListContract.Presenter

        fun updateWeatherList() //날씨 관련 UI 업데이트
        fun notifyAdapter(isChange : Boolean) //어뎁터 변경 알림


    }


    interface Presenter {
        var weatherData : GetWeatherData
        var view : WeatherListContract.View

        fun getWeatherList() // 날씨 관련 데이터 요청


    }
}
package com.example.weatherclothselection.Presenter

interface WeatherListContract {
    interface View {
        var presenter : WeatherListContract.Presenter

        fun updateWeatherList() //날씨 관련 UI 업데이트
        fun notifyAdapter() 


    }


    interface Presenter {



    }
}
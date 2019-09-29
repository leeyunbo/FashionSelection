package com.example.weatherclothselection.Presenter

interface WeatherListContract {
    interface View {
        var presenter : WeatherListContract.Presenter

        fun updateWeatherList() //날씨 관련 UI 업데이트
        fun notifyAdapter() //어뎁터 변경 알림


    }


    interface Presenter {

        fun requestWeatherList() // 날씨 관련 데이터 요청


    }
}
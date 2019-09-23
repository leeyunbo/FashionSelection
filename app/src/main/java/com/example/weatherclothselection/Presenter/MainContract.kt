package com.example.weatherclothselection.Presenter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

interface MainContract {

    interface View {
        fun updateWeatherUI(max_temp : String, min_temp : String, wet : String, weather : String) //날씨 관련 UI 업데이트
    }

    interface Presenter {
        fun getWeatherData() //날씨 관련 데이터 가져오기

    }
}

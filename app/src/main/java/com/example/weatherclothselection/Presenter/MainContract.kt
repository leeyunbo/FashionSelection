package com.example.weatherclothselection.Presenter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherclothselection.Data.GetWeatherData
import java.io.InputStream

interface MainContract {

    interface View {
        fun updateWeatherUI(entries : List<*>) //날씨 관련 UI 업데이트
    }

    interface Presenter {
        var view : View
        var weatherData : GetWeatherData
        var inputStream : InputStream

        fun getWeatherData() //날씨 관련 데이터 가져오기
    }
}

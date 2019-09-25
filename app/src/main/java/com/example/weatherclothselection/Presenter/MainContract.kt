package com.example.weatherclothselection.Presenter

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import com.example.weatherclothselection.Data.Entry
import com.example.weatherclothselection.Data.GetWeatherData
import java.io.InputStream

interface MainContract {

    interface View {
        var presenter : MainContract.Presenter

        fun updateWeatherUI() //날씨 관련 UI 업데이트
        fun networkConnect()  //네트워크 연결 시작
        fun loadXmlFromNetwork(urlString : String) // XML 접근 후 Stream 가져오기
        fun notifyAdapter(entries : List<Entry>) // 완료되었을 때 알림
    }

    interface Presenter {
        var view : View
        var weatherData : GetWeatherData

        fun ObtainWeatherData(inputStream: InputStream) //날씨 관련 데이터 가져오기
    }
}

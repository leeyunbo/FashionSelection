package com.example.weatherclothselection

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.weatherclothselection.Data.Entry
import com.example.weatherclothselection.Presenter.MainContract
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),MainContract.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun updateWeatherUI(entries : List<*>) {
        

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

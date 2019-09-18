package com.example.weatherclothselection

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherclothselection.Presenter.MainContract

class MainActivity : AppCompatActivity(),MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

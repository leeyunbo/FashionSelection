package com.example.weatherclothselection

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.util.Log
import android.view.Window
import android.widget.TextView
import com.example.weatherclothselection.Data.Entry
import com.example.weatherclothselection.Data.GetWeatherData
import com.example.weatherclothselection.Presenter.MainContract
import com.example.weatherclothselection.Presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.xmlpull.v1.XmlPullParserException
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(),MainContract.View {
    override lateinit var presenter : MainContract.Presenter
    var temp_max: String? = null
    var temp_min: String? = null
    var time: String? = null
    var weather: String? = null
    var isFinish : Boolean? = null
    var pubDate : String? = null
    var wet : String? = null
    var category : String? = null

    companion object {
        const val WIFI = "Wi-Fi"
        const val ANY = "Any"
        const val SO_URL = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4111757000"
        private var wifiConnected = false
        private var mobileConnected = false
        var refreshDisplay = true
        var sPref : String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter().apply {
            view = this@MainActivity
            weatherData = GetWeatherData()
        }
        networkConnect()
    }

    override fun notifyAdapter(entries:List<Entry>) {
        isFinish = true
        this.temp_max = entries[2].tmx
        this.temp_min = entries[2].tmn
        this.weather = entries[0].wfKor
        this.time = entries[0].tm
        this.wet  = entries[0].reh
        this.pubDate = entries[0].pubDate
        this.category = entries[0].category
    }

    override fun updateWeatherUI() {
        temp_text_view.setText(temp_min+"/" + temp_max)
        wet_text_view.setText(wet + "%")
        weather_text_view.setText(weather)
        time_text_View.setText(pubDate)
        area_text_view.setText(category)

        var temp = temp_min?.toInt()


        if (temp?.compareTo(27) == 0) {
            cloth_image_view.setImageResource(R.drawable.)

        }


    }

    override fun networkConnect() {
        DownloadXmlTask().execute(SO_URL)
    }

    private inner class DownloadXmlTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg urls: String) : String{
            loadXmlFromNetwork(urls[0])
            return "Connect Success"
        }

        override fun onPostExecute(result: String?) {
            while(isFinish == false) {
                Log.i("wait","wait")
            }
            updateWeatherUI()
            super.onPostExecute(result)
        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    override fun loadXmlFromNetwork(urlString: String)  {
        downloadUrl(urlString)?.use { presenter.ObtainWeatherData(it)
        }
    }

    @Throws(IOException::class)
    private fun downloadUrl(urlString : String) : InputStream? {
        val url = URL(urlString)
        return (url.openConnection() as? HttpURLConnection)?.run {
            readTimeout = 10000
            connectTimeout = 15000
            requestMethod = "GET"
            doInput = true
            connect()
            inputStream
        }


    }
}


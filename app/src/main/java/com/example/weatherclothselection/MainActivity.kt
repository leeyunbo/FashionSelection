package com.example.weatherclothselection

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
    override var temp_max: String?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var temp_min: String?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var time: String?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var weather: String?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    var isFinish : Boolean? = null

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
        this.temp_max = entries[0].tmx
        this.temp_min = entries[0].tmn
        this.weather = entries[0].wfKor
        this.time = entries[0].tm
    }

    override fun updateWeatherUI() {
        temp_text_view.setText(temp_min+"/" + temp_max)
        wet_text_view.setText("36%")
        weather_text_view.setText(weather)
        time_text_View.setText(time)
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


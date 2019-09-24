package com.example.weatherclothselection

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.weatherclothselection.Data.Entry
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
    lateinit var presenter : MainContract.Presenter

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
        }
        networkConnect()
    }

    override fun updateWeatherUI(entries : List<Entry>) {
        temp_text_view.setText(entries[0].tmn +"/" + entries[0].tmx)
        wet_text_view.setText(entries[0].reh +"%")
        weather_text_view.setText(entries[0].wfKor)
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
            super.onPostExecute(result)
        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    override fun loadXmlFromNetwork(urlString: String)  {
        downloadUrl(urlString)?.use { presenter.getWeatherData(it)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
    }


    @Throws(IOException::class)
    private fun downloadUrl(urlString : String) : InputStream? {
        val url = URL(urlString)
        val httpClient = url.openConnection() as HttpURLConnection
        lateinit var stream : InputStream
        httpClient.requestMethod = "GET"
        httpClient.connectTimeout = 10000
        httpClient.readTimeout =  15000
        httpClient.doInput = true



        stream = BufferedInputStream(httpClient.inputStream)


        return stream


    }
}


package com.example.weatherclothselection

import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import com.example.weatherclothselection.Adapter.WeatherAdapter
import com.example.weatherclothselection.Data.Entry
import com.example.weatherclothselection.Data.GetWeatherData
import com.example.weatherclothselection.Presenter.WeatherListContract
import com.example.weatherclothselection.Presenter.WeatherListPresenter

import kotlinx.android.synthetic.main.activity_weather_list.*
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class WeatherListActivity : AppCompatActivity(),WeatherListContract.View {

    lateinit override var presenter: WeatherListContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)

        presenter = WeatherListPresenter().apply {
            view = this@WeatherListActivity
            weatherData = GetWeatherData()
        }

        networkConnect()

    }

    override fun requestWeatherList() : List<Entry> {
        return presenter.getWeatherList()
    }

    override fun updateWeatherList(isChange: Boolean, weather_list: List<Entry>) {

        val weatherAdapter = WeatherAdapter(this, weather_list)
        weather_list_view.adapter = weatherAdapter

    }

    override fun networkConnect() {
        DownloadXmlTask().execute(MainActivity.SO_URL)
    }

    private inner class DownloadXmlTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg urls: String) : String{
            loadXmlFromNetwork(urls[0])
            return "Connect Success"
        }

        override fun onPostExecute(result: String?) {
            updateWeatherList(true,requestWeatherList())
            super.onPostExecute(result)
        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    override fun loadXmlFromNetwork(urlString: String)  {
        downloadUrl(urlString)?.use { presenter.getWeatherList(it)
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

package com.example.weatherclothselection.Data

import android.content.res.Resources
import android.os.DropBoxManager
import android.util.Log
import android.util.Xml
import com.example.weatherclothselection.MainActivity
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream
import java.lang.IllegalStateException
import java.net.URL
import java.security.KeyStore

private val ns: String? = null

data class Entry (
    val tmx : String?, //최대 온도
    val tmn : String?, //최소 온도
    val wfKor : String?, //날씨
    val reh : String?, //습도
    val tm : String?,  //시간
    val pubDate : String?, //날짜
    val category : String? //장소 
)


class GetWeatherData {

    @Throws(XmlPullParserException::class, IOException::class)
    fun getWeatherData(inputStream : InputStream) : List<Entry> {
        Log.i("getWeatherData","Start")
        inputStream.use { inputStream ->
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            parser.nextTag()
            return readData(parser)
        }

    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readData(parser : XmlPullParser) : List<Entry> {
        val entries = mutableListOf<Entry>()
        var tmx : String? = null
        var tmn : String? = null
        var wfKor : String? = null
        var reh : String? = null
        var tm : String? = null
        var pubDate : String? = null
        var category : String? = null

        var eventType = parser.eventType

        while(eventType != XmlPullParser.END_DOCUMENT) {
            when(eventType) {
                XmlPullParser.START_TAG ->
                    when(parser.name) {
                        "tmx"  -> tmx = readData(parser, "tmx")
                        "tmn" -> tmn = readData(parser, "tmn")
                        "wfKor" -> wfKor = readData(parser, "wfKor")
                        "reh" -> reh = readData(parser, "reh")
                        "tm" -> tm = readData(parser, "tm")
                        "pubDate" -> pubDate = readData(parser, "pubDate")
                        "category" -> category = readData(parser, "category")
                    }
                XmlPullParser.END_TAG ->
                    if(parser.name=="data") {
                        entries.add(Entry(tmx,tmn,wfKor,reh,tm,pubDate,category))

                    }

            }
            eventType = parser.next()
        }

        return entries

    }


    @Throws(IOException::class, XmlPullParserException::class)
    private fun readData(parser : XmlPullParser, want_tag : String) : String {
        Log.i("readData","Start")
        parser.require(XmlPullParser.START_TAG, ns, want_tag)
        val data = readText(parser)
        parser.require(XmlPullParser.END_TAG, ns, want_tag)
        return data
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun readText(parser : XmlPullParser) : String {
        Log.i("readText","Start")
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }

        return result
    }

    @Throws(IOException::class, XmlPullParserException::class)
    private fun skip(parser : XmlPullParser) {
        Log.i("skip","Start")
        if (parser.eventType != XmlPullParser.START_TAG) {
            throw IllegalStateException()
        }
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }




}
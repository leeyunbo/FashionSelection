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
    val tmx : String?,
    val tmn : String?,
    val wfKor : String?,
    val reh : String?,
    val tm : String?
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
            return readFeed(parser)
        }

    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readFeed(parser : XmlPullParser) : List<Entry> {
        Log.i("readFeed","Start")
        val entries = mutableListOf<Entry>()

        while (parser.next() != XmlPullParser.END_TAG) {
            if(parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            if (parser.name == "data") {
                entries.add(readEntry(parser))
            } else {
                skip(parser)
            }
        }

        return entries

    }
    @Throws(IOException::class, XmlPullParserException::class)
    private fun readEntry(parser : XmlPullParser) : Entry {
        Log.i("readEntry","Start")
        parser.require(XmlPullParser.START_TAG, ns, "entry")
        var tmx : String? = null
        var tmn : String? = null
        var wfKor : String? = null
        var reh : String? = null
        var tm : String? = null
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                "tmx"  -> tmx = readData(parser, "tmx")
                "tmn" -> tmn = readData(parser, "tmn")
                "wkKor" -> wfKor = readData(parser, "wkKor")
                "reh" -> reh = readData(parser, "reh")
                "tm" -> tm = readData(parser, "tm")
            }
        }
        return Entry(tmx,tmn,wfKor,reh,tm)
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
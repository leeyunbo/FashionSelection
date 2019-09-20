package com.example.weatherclothselection.Data

import android.content.res.Resources
import android.os.DropBoxManager
import android.util.Xml
import com.example.weatherclothselection.MainActivity
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream
import java.net.URL
import java.security.KeyStore

//"http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4111757000"
class GetWeatherData {

    fun GetWeatherData() : List<*> {
        lateinit var inputStream : InputStream
        inputStream.use { inputStream ->
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            parser.nextTag()
            return readFeed(parser)
        }

    }

    fun readFeed(parser : XmlPullParser) : List<KeyStore.Entry> {
        val entries = mutableListOf<KeyStore.Entry>()

        parser.require(XmlPullParser.START_TAG,ns,"feed")

    }

}
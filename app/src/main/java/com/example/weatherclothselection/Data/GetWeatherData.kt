package com.example.weatherclothselection.Data

import android.content.res.Resources
import android.os.DropBoxManager
import android.util.Xml
import com.example.weatherclothselection.MainActivity
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.security.KeyStore

private val ns: String? = null

//"http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4111757000"
class GetWeatherData {

    @Throws(XmlPullParserException::class, IOException::class)
    fun GetWeatherData(inputStream : InputStream) : List<*> {
        inputStream.use { inputStream ->
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            parser.nextTag()
            return readFeed(parser)
        }

    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readFeed(parser : XmlPullParser) : List<KeyStore.Entry> {
        val entries = mutableListOf<KeyStore.Entry>()

        parser.require(XmlPullParser.START_TAG,ns,"feed")
        while (parser.next() != XmlPullParser.END_TAG) {
            if(parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            if (parser.name == "channel") {
                entries.add()
            }
        }

    }

}
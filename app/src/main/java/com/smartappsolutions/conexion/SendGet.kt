package com.smartappsolutions.conexion

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

open class SendGet : AsyncTask<String, Void, String>() {

    internal var TAG = "SendGet"
    internal var url: String =""
    protected var res: String =""
    protected var context:Context? = null

    init {

        Log.d(TAG, "hello asynktask")
    }

    fun setConfig(url: String, context:Context) {

        this.url = url
        this.context =context
        this.execute()
    }

    override fun doInBackground(vararg params: String): String {

        // String url = "http://192.168.0.16/test/service.php?opcion=1&login=djf&clave=1234";

        try {

            var obj = URL(url)
            var mHttpURLConnection = obj.openConnection() as HttpURLConnection

            mHttpURLConnection.requestMethod = "GET"
            var responseCode = mHttpURLConnection.responseCode

            var `in` = BufferedReader(InputStreamReader(mHttpURLConnection.inputStream))
            var inputLine: String? =null
            var response = StringBuffer()

            inputLine = `in`.readLine()

            while ( inputLine!= null) {
                response.append(inputLine)
                inputLine = `in`.readLine()
            }
            `in`.close()
            res = response.toString()
            Log.d(TAG, response.toString())

        } catch (e: Exception) {
            Log.d(TAG, "error $e")

            res = "-1"
        }


        return res

    }

    override fun onPostExecute(s: String) {
        super.onPostExecute(s)

        Log.d(TAG, res)
    }
}

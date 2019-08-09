package com.smartappsolutions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.Volley;
import kotlinx.android.synthetic.main.activity_volley_test.*

class VolleyTestActivity : AppCompatActivity() {

    val TAG ="VolleyTestActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volley_test)
        Log.d(TAG,"HELLO "+TAG);

        val url = "http://192.168.1.142:8000/api/asuntos"
        val requestQueue = Volley.newRequestQueue(this@VolleyTestActivity)

        Enviar.setOnClickListener {
            Log.d(TAG,"onClick")

            val stringRequest = object : StringRequest(Request.Method.GET, url,
                Response.Listener { response ->
                    /*Toast.makeText(applicationContext, "hello volley $response", Toast.LENGTH_LONG).show()*/
                    Log.d(TAG,response)

                    response_tv.setText(response)
                    requestQueue.stop()
                },
                Response.ErrorListener { error ->
                    Toast.makeText(applicationContext, "error $error", Toast.LENGTH_LONG).show()
                }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {

                    val params = HashMap<String, String>()
                    params["X-Parse-REST-API-Key"] = "bsiWfUOJ7vhiongFPQM9PTmeqCUoKJZM8HezfZGx"
                    params["X-Parse-Application-Id"] = "UioTROrtK4RxrhKB7n2k2hOXEfTyScQNto9I0zTV"

                    return params
                }
            }

            requestQueue.add(stringRequest)
        }



    }
}
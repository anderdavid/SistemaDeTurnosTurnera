package com.smartappsolutions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_test_conection.*
import com.smartappsolutions.conexion.SendGet



class TestConectionActivity : AppCompatActivity() {

    val TAG ="TestConectionActivity";
    /*val context =applicationContext*/
    val msg ="error"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_conection)

        Log.d(TAG,"hello world "+TAG)
        Toast.makeText(applicationContext,TAG+" HELLO WORLD ",Toast.LENGTH_SHORT).show()

         this.btn_testGet.setOnClickListener {
             Toast.makeText(applicationContext,"onClick",Toast.LENGTH_SHORT).show()
          this.testGet.setText("onClick")
             testGet().setConfig("http://192.168.1.142:8000/api/asuntos",applicationContext)

        }
    }



   private inner class testGet : SendGet() {

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            Log.d(TAG, res)

            if (res.equals("-1")) {

                Toast.makeText(applicationContext, "Error en la conexion", Toast.LENGTH_LONG).show()
            } else {
                testGet.setText("url test:" + url + " \n  Respuesta: " + res.toString())
            }
        }
    }
}

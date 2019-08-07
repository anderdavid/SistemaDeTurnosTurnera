package com.smartappsolutions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class HelloActivity : AppCompatActivity() {

    val TAG ="HelloActivity";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hello)

        Log.d(TAG,"Hello World")
        Toast.makeText(applicationContext,"Hello world", Toast.LENGTH_LONG).show()
    }
}

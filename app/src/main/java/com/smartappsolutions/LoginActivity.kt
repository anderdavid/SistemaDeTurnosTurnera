package com.smartappsolutions

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val TAG= "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_login)
       /* setSupportActionBar(toolbar)*/

        Log.d(TAG,"hello world "+TAG)

       /* fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }

}

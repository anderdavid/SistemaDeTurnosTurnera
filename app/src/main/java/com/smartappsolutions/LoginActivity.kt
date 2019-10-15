package com.smartappsolutions

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.smartappsolutions.ConnectionManager.Api

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

class LoginActivity : AppCompatActivity() {

    val TAG= "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_login)
        Log.d(TAG,"hello world "+TAG)

        this.ingresar_btn.setOnClickListener(View.OnClickListener {
            Log.d(TAG,"ingresar_btn.setOnClickListener onClick")

            if(!EmptyValidation()){
                Log.d(TAG,"error por campos vacios")
            }else if(!isEmailValid(this.login_edt.text.toString())){
                Log.d(TAG,"error email no valido")
                Toast.makeText(applicationContext,"Email no valido",Toast.LENGTH_SHORT).show()
            }else{
                Log.d(TAG,"campos validados")
                Log.d(TAG,"ingresar a: "+ Api.AUTENTICATION_URL)

            }


        })

    }

    fun EmptyValidation(): Boolean {
        Log.d(TAG,"EmptyValidation()");

        if(this.login_edt.text.isEmpty()){
            Toast.makeText(applicationContext,"Campo login no puede estar vacio",Toast.LENGTH_SHORT).show()
            return false
        }else if(this.password_edt.text.isEmpty()){
            Toast.makeText(applicationContext,"Campo password no puede estar vacio",Toast.LENGTH_SHORT).show()
            return false

        }else{
            return true
        }
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}

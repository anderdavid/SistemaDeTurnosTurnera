package com.smartappsolutions

import android.content.Intent
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
import com.android.volley.toolbox.StringRequest
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley
import org.json.JSONObject






class LoginActivity : AppCompatActivity() {

    val TAG= "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_login)
        Log.d(TAG,"hello world "+TAG)

        val requestQueue = Volley.newRequestQueue(this@LoginActivity)

        this.ingresar_btn.setOnClickListener(View.OnClickListener {
            Log.d(TAG,"ingresar_btn.setOnClickListener onClick")

            if(!EmptyValidation()){
                Log.d(TAG,"error por campos vacios")
            }else if(!isEmailValid(this.login_edt.text.toString())){
                Log.d(TAG,"error email no valido")
                Toast.makeText(applicationContext,"Email no valido",Toast.LENGTH_SHORT).show()
            }else{

                Log.d(TAG,"ingresar a: "+ Api.AUTENTICATION_URL)

                val postRequest = object : StringRequest(Request.Method.POST,  Api.AUTENTICATION_URL,
                    Response.Listener { response ->
                        // response
                        Log.d(TAG, response)

                        val jsonResponse = JSONObject(response.toString())
                        val status = jsonResponse.getString("status")
                        Log.d(TAG, "status: $status")

                        if (status.equals("true")) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish();
                        }else{
                            Toast.makeText(applicationContext,jsonResponse.getString("msg"),Toast.LENGTH_SHORT).show()
                        }

                    },
                    Response.ErrorListener { error ->
                        // error

                        Log.d(TAG,error.toString())
                        Toast.makeText(applicationContext, "error $error", Toast.LENGTH_LONG).show()
                    }
                ) {

                    override fun getParams(): Map<String, String> {
                        val params = HashMap<String, String>()
                        params["email"] = login_edt.text.toString()
                        params["password"] = password_edt.text.toString()


                        return params
                    }
                }
                requestQueue.add(postRequest)

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

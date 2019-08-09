package com.smartappsolutions

import android.app.ListActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.ArrayAdapter
import android.widget.ListView
import android.content.Intent




class MyListApp : ListActivity() {

    val TAG ="MyListApp"

    val testActivitys = arrayOf(
        "TestConectionActivity",
        "MainActivity",
        "VolleyTestActivity"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      /*  setContentView(R.layout.my_list_app)*/
        Log.d(TAG,"hello world "+TAG);

        listAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, testActivitys)


      /*  Toast.makeText(applicationContext,"Hello world "+TAG,Toast.LENGTH_SHORT).show()*/
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)

      /*  Toast.makeText(applicationContext,"onClick",Toast.LENGTH_SHORT).show();*/

        val nombreActivity = testActivitys[position]

        try {

            val clazz = Class.forName("com.smartappsolutions.$nombreActivity")
            val intent = Intent(this@MyListApp, clazz)
            startActivity(intent)

        } catch (e: Exception) {

        }

    }
}

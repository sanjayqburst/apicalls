package com.example.apicalls

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var color= arrayOf("#F3FFE3","#C4FAF8","#FFFFD1","#DCD3FF","#FFCBC1","#AFF8DB")
        var jsonData=URL("https://reqres.in/api/users?page=2").readText()
        println(jsonData)
        val btn=findViewById<Button>(R.id.buttonString)
        btn.setOnClickListener {
            val number=(0..5).random()
            println(number)
        }
    }
}
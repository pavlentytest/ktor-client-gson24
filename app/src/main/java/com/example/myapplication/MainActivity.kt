package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class User(var id: Int, var name: String, val age: Int)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val client = HttpClient(){
            install(ContentNegotiation) {
                gson()
            }
        }
        GlobalScope.launch {
            val httpResponse = client.get("http://10.0.2.2:8080/all")
            val stringBody : List<User> = httpResponse.body()
            Log.d("RRR",stringBody.get(0).toString())
        }

    }
}
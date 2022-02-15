package com.example.servidorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.servidorapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var token = intent.getStringExtra("token")
        preguntanueva(token!!)

    }


    fun preguntanueva(token: String){
        val client = OkHttpClient()
        val request = Request.Builder()
        request.url("http://10.0.2.2:8082/getPregunta/${token}")

        val call = client.newCall(request.build())
        call.enqueue( object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.toString())
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(this@MainActivity, "Algo ha ido mal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                println(response.toString())
                response.body?.let { responseBody ->
                    val body = responseBody.string()
                    println(body)
                    val gson = Gson()

                    val pregunta = gson.fromJson(body, Preguntas::class.java)

                    CoroutineScope(Dispatchers.Main).launch {

                        binding.tv1.text = pregunta.nombre
                        binding.bt1.text = pregunta.respuesta1.toString()
                        binding.bt2.text = pregunta.respuesta2.toString()
                        binding.bt3.text = pregunta.respuesta3.toString()
                        binding.bt4.text = pregunta.respuesta4.toString()
                    }

                    binding.bt1.setOnClickListener {
                        (responder(binding.bt1, token, pregunta.id))
                    }

                    binding.bt2.setOnClickListener {
                        (responder(binding.bt2, token, pregunta.id))
                    }


                    binding.bt3.setOnClickListener {
                        (responder(binding.bt3, token, pregunta.id))
                    }


                    binding.bt4.setOnClickListener {
                        (responder(binding.bt4, token, pregunta.id))
                    }
                }
            }
        })
    }

    fun responder(boton: Button, token: String, id: Int,){
        val client = OkHttpClient()
        val boton = boton.text.toString()
        val request = Request.Builder()
        request.url("http://10.0.2.2:8082/getPregunta/${boton}/${id}/${token}")


        val call = client.newCall(request.build())
        call.enqueue( object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.toString())
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(this@MainActivity, "Algo ha ido mal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                println(response.toString())
                response.body?.let { responseBody ->
                    val body = responseBody.string()
                    println(body)


                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(this@MainActivity, body, Toast.LENGTH_SHORT).show()
                        preguntanueva(token)
                    }

                }
            }
        })
    }
}

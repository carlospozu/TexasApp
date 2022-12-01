package com.example.servidorapp

import com.example.demo.Jugadores
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class CrearJugRequest {

    companion object {

        private var gson = Gson()

        fun get( nombre: String, stack: Int) {

            val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://10.0.2.2:8084/jugador/${nombre}/${stack}")
                    .build()
                val response = client.newCall(request).execute()


        }
    }

}
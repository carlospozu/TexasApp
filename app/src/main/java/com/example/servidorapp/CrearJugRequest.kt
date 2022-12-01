package com.example.servidorapp

import com.example.demo.Jugadores
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class CrearJugRequest {

    companion object {

        private var gson = Gson()

        fun get( nombre: String, stack: Int): ListaJugadores {
            val listaJug = ListaJugadores()
            val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://10.0.2.2:8084/jugador/${nombre}/${stack}")
                    .build()
                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.string().let { responseBody ->
                        val jugador = gson.fromJson(responseBody, Jugadores::class.java)

                        listaJug.agregar(jugador)
                    }

                } else
                    println("Algo ha ido mal")

            return listaJug
        }
    }

}
package com.example.servidorapp


import okhttp3.OkHttpClient
import okhttp3.Request

class LoginRequest {

    companion object {
        fun get(usuario: String, contrasena: String){
            val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://10.0.2.2:8082/crearUsuario/${usuario}/${contrasena}")
                    .build()
                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.string().let { responseBody ->

                        println("TODO BIEN")
                    }

                } else
                    println("Algo ha ido mal")


            }
        }
}


/*
companion object {
        fun get(usuario: String, contrasena: String){
            val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://10.0.2.2:8082/crearUsuario/${usuario}/${contrasena}")
                    .build()
                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.string().let { responseBody ->

                        println("TODO BIEN")
                    }

                } else
                    println("Algo ha ido mal")


            }
        }
}
 */
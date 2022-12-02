package com.example.servidorapp


import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class CheckRequest {

    companion object {

        private var gson = Gson()

        fun get(  id: Int) {

            val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://10.0.2.2:8084/check/${id}")
                    .build()
                val response = client.newCall(request).execute()


        }
    }

}
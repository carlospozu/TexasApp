package com.example.servidorapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.servidorapp.databinding.LoginBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            login(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }

    }

    fun login(usuario: String, contrasena: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
        request.url("http://10.0.2.2:8082/Registro/${usuario}/${contrasena}")
        val call = client.newCall(request.build())
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.toString())
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(this@LoginActivity, "Algo ha ido mal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                println(response.toString())
                response.body?.let { responseBody ->
                    val body = responseBody.string()
                    println(body)
                    val gson = Gson()
                    CoroutineScope(Dispatchers.Main).launch {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.putExtra("token", body)
                        startActivity(intent)
                    }

                }

            }
        })
    }
}
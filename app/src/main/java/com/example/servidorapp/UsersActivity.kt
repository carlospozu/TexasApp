package com.example.servidorapp

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.servidorapp.databinding.UsuariosBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException


class UsersActivity : AppCompatActivity() {


    private lateinit var binding: UsuariosBinding
    lateinit var adapter: AdapterUsers
    private lateinit var recyvlerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyvlerView = findViewById(R.id.rvUsers)

        val players = intent.getIntExtra("players", 5)
        val stack = intent.getIntExtra("stack", 20000)
        adapter(players, stack)



    binding.avanzar.setOnClickListener{
                val intent = Intent(this@UsersActivity, JuegoActivity::class.java)
                intent.putExtra("players", players)
                startActivity(intent)
        }
    }



    fun borrar() {
        val client = OkHttpClient()
        val request = Request.Builder()
        request.url("http://10.0.2.2:8084/borrar")
        val call = client.newCall(request.build())
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.toString())
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(this@UsersActivity, "Algo ha ido mal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                println(response.toString())
                response.body?.let { responseBody ->
                    val body = responseBody.string()
                    println(body)
                    CoroutineScope(Dispatchers.Main).launch {
                        print("Correcto")
                    }

                }

            }
        })
    }

    fun adapter(players: Int, stack: Int) {
        adapter = AdapterUsers(players, stack)
        recyvlerView.layoutManager = LinearLayoutManager(this@UsersActivity)
        recyvlerView.adapter = adapter

    }
}



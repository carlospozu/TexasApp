package com.example.servidorapp

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.servidorapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private lateinit var next: Button
    private lateinit var players: EditText
    private lateinit var stacks: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

       //  val players = binding.numJug.text
       // val stack = binding.numFichas.text


        next = findViewById(R.id.next)
        players = findViewById(R.id.num_jug)
        stacks = findViewById(R.id.num_fichas)




    next.setOnClickListener {
        val player = players.text.toString().toInt()
        val stack = stacks.text.toString().toInt()
        val intent = Intent(this@MainActivity, UsersActivity::class.java)
        intent.putExtra("players", player)
        intent.putExtra("stack", stack)
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
                    Toast.makeText(this@MainActivity, "Algo ha ido mal", Toast.LENGTH_SHORT).show()
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
}



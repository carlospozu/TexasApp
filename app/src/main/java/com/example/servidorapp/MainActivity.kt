package com.example.servidorapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

import androidx.appcompat.app.AppCompatActivity
import com.example.servidorapp.databinding.ActivityMainBinding

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

        val player = players.text
        val stack = stacks.text


    next.setOnClickListener {
    val intent = Intent(this@MainActivity, UsersActivity::class.java)
    intent.putExtra("players", player)
    intent.putExtra("stack", stack)
    startActivity(intent)
        }
    }
}



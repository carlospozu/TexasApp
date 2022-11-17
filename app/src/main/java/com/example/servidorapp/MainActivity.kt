package com.example.servidorapp

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.servidorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        val players = binding.numJug.text.toString().toInt()
        val stack = binding.numFichas.text.toString().toInt()


binding.next.setOnClickListener {
    val intent = Intent(this@MainActivity, UsersActivity::class.java)
    intent.putExtra("players", players)
    intent.putExtra("stack", stack)
    startActivity(intent)
        }
    }
}



package com.example.servidorapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.servidorapp.databinding.UsuariosBinding


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

    fun adapter(players: Int, stack: Int) {
        adapter = AdapterUsers(players, stack)
        recyvlerView.layoutManager = LinearLayoutManager(this@UsersActivity)
        recyvlerView.adapter = adapter

    }
}



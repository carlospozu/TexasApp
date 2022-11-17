package com.example.servidorapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servidorapp.databinding.ActivityMainBinding
import com.example.servidorapp.databinding.JugadoresBinding
import com.example.servidorapp.databinding.LoginBinding
import com.example.servidorapp.databinding.UsuariosBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class JuegoActivity : AppCompatActivity() {


    private lateinit var binding: JugadoresBinding
    lateinit var adapter: AdapterUsers
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = JugadoresBinding.inflate(layoutInflater)
        setContentView(R.layout.jugadores)


      //  adapter(players)



   /* binding.avanzar.setOnClickListener {
        val intent = Intent(this@JuegoActivity, JuegoActivity::class.java)
        intent.putExtra("players", players)
        intent.putExtra("stack", stack)
        startActivity(intent)
        }
    }



    fun adapter(players: Int) {
        adapter = AdapterUsers(players)
        binding.rvUsers.layoutManager = LinearLayoutManager(this@JuegoActivity)
        binding.rvUsers.adapter = adapter
    }

    */
}}



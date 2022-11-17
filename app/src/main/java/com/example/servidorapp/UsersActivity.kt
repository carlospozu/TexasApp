package com.example.servidorapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servidorapp.databinding.ActivityMainBinding
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

class UsersActivity : AppCompatActivity() {


    private lateinit var binding: UsuariosBinding
    lateinit var adapter: AdapterUsers
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UsuariosBinding.inflate(layoutInflater)
        setContentView(R.layout.usuarios)
        val listaUser: MutableList<Usuarios> = mutableListOf()
        val players = intent.getIntExtra("players", 0)
        val stack = intent.getIntExtra("stack", 0)

        adapter(players, listaUser, stack)



    binding.avanzar.setOnClickListener {
        val intent = Intent(this@UsersActivity, JuegoActivity::class.java)
        startActivity(intent)
        }
    }

    fun adapter(players: Int, lista: MutableList<Usuarios>, stack: Int) {
        adapter = AdapterUsers(players, lista, stack)
        binding.rvUsers.layoutManager = LinearLayoutManager(this@UsersActivity)
        binding.rvUsers.adapter = adapter
    }
}



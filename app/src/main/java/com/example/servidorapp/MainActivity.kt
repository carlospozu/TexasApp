package com.example.servidorapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.servidorapp.databinding.ActivityMainBinding
import com.example.servidorapp.databinding.LoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val players =binding.numJug.text
        val stack = binding.numFichas.text

        var token = intent.getStringExtra("token")

        val intent = Intent(this@MainActivity, MainActivity::class.java)
        intent.putExtra("token", body)
        startActivity(intent)

    }
}



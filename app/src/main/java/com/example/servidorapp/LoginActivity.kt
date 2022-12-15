package com.example.servidorapp

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.servidorapp.databinding.LoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginBinding
    private lateinit var btnLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        binding = LoginBinding.inflate(layoutInflater)

        btnLogin = findViewById(R.id.btnLogin)
        etEmail = findViewById(R.id.et_email)
        password = findViewById(R.id.password)

         btnLogin.setOnClickListener{
             val passwordCifrada = cifrar(binding.password.text.toString(), generarToken())
             val email = etEmail.text.toString()
          //LoginRequest.get(etEmail.text.toString(), passwordCifrada)
            login(email, passwordCifrada)
             val intent = Intent(this@LoginActivity, MainActivity::class.java)
             startActivity(intent)
        }

    }




    fun login(usuario: String, contrasena: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
        request.url("http://10.0.2.2:8084/crearUsuario/${usuario}/${contrasena}")
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
                    CoroutineScope(Dispatchers.Main).launch {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }

                }

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        borrar()
        Toast.makeText( this@LoginActivity,"Correctisimo", Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(this@LoginActivity, "Algo ha ido mal", Toast.LENGTH_SHORT).show()
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

    private fun cifrar(textoEnString : String, llaveEnString : String) : String {
        println("Voy a cifrar: $textoEnString")
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, getKey(llaveEnString))
        val textCifrado = android.util.Base64.encodeToString(cipher.doFinal(textoEnString.toByteArray(Charsets.UTF_8)), android.util.Base64.URL_SAFE)
        println("He obtenido $textCifrado")
        return textCifrado
    }

    private fun getKey(llaveEnString : String): SecretKeySpec {
        var llaveUtf8 = llaveEnString.toByteArray(Charsets.UTF_8)
        val sha = MessageDigest.getInstance("SHA-1")
        llaveUtf8 = sha.digest(llaveUtf8)
        llaveUtf8 = llaveUtf8.copyOf(16)
        return SecretKeySpec(llaveUtf8, "AES")
    }

    fun generarToken():String{
        val alphabet = 'a'..'z'
        var generado =""
        for (i in 1..7){
            val randomLetter = alphabet.random()
            generado+=randomLetter
        }
        return generado
    }







}
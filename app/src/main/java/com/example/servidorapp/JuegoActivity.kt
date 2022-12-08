package com.example.servidorapp

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.servidorapp.databinding.JugadoresBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.IOException

class JuegoActivity : AppCompatActivity() {


    private lateinit var binding: JugadoresBinding
    lateinit var adapter: AdapterJugadores
    private lateinit var recyvlerView: RecyclerView
    private lateinit var listaJugg: ListaJugadores


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = JugadoresBinding.inflate(layoutInflater)
        setContentView(R.layout.jugadores)
        recyvlerView = findViewById(R.id.rvJuego)
        val players = intent.getIntExtra("players", 5)
        llamada(players)
}


   fun llamada(players: Int){
        lifecycleScope.launch(Dispatchers.IO) {
            var listaJugadores = mutableListOf<Jugadores>()
             listaJugg = DescargarJugRequest.get(players)
            withContext(Dispatchers.Main) {
                adapter(players, listaJugg)
            }
        }
    }


    fun adapter(players: Int, listaJugadores: ListaJugadores) {
        adapter = AdapterJugadores(players, listaJugadores)
        recyvlerView.layoutManager = LinearLayoutManager(this@JuegoActivity)
        recyvlerView.adapter = adapter
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
                    Toast.makeText(this@JuegoActivity, "Algo ha ido mal", Toast.LENGTH_SHORT).show()
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



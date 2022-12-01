package com.example.servidorapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.Jugadores
import com.example.servidorapp.databinding.JugadoresBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JuegoActivity : AppCompatActivity() {


    private lateinit var binding: JugadoresBinding
    lateinit var adapter: AdapterJugadores
    private lateinit var recyvlerView: RecyclerView

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
            listaJugadores = DescargarJugRequest.get(players)
            withContext(Dispatchers.Main) {
                adapter(players)
            }
        }
    }
    fun adapter(players: Int) {
        adapter = AdapterJugadores(players)
        recyvlerView.layoutManager = LinearLayoutManager(this@JuegoActivity)
        recyvlerView.adapter = adapter

    }

}



package com.example.servidorapp

import android.os.Bundle
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.servidorapp.databinding.JugadoresBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JuegoActivity : AppCompatActivity() {


    private lateinit var binding: JugadoresBinding
    lateinit var adapter: AdapterJugadores
    private lateinit var recyvlerView: RecyclerView
    private lateinit var listaJugg: ListaJugadores
    private lateinit var bote: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = JugadoresBinding.inflate(layoutInflater)
        setContentView(R.layout.jugadores)
        recyvlerView = findViewById(R.id.rvJuego)
        bote = findViewById(R.id.bote)



        val players = intent.getIntExtra("players", 5)
        llamada(players, bote)
}

  /*  fun subirBote(boteActual: Int){
        bote.text = boteActual.toString()
    }

   */

   fun llamada(players: Int, bote: TextView){
        lifecycleScope.launch(Dispatchers.IO) {
           // var listaJugadores = mutableListOf<Jugadores>()
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





}



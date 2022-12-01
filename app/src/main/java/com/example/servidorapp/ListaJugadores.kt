package com.example.servidorapp

import com.example.demo.Jugadores
import com.google.gson.Gson


class ListaJugadores(var listaJug : MutableList<Jugadores> = mutableListOf()) {

    companion object {
        fun fromJson(json: String):ListaJugadores {
            val gson = Gson()
            return gson.fromJson(json, ListaJugadores::class.java)
        }
    }

    fun agregar(jugador: Jugadores) {
        listaJug.add(jugador)
    }

    fun toJson(): String{
        val gson = Gson()
        return gson.toJson(this)
    }
}
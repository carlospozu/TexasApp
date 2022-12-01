package com.example.demo

import com.google.gson.Gson


data class Jugadores (var nombre: String, var stack: Int){

    var id = 0
    var turno= false
    var vivo= true
    var bb = false
    var sb= false
    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }
}
package com.example.servidorapp

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.Jugadores
import com.example.servidorapp.databinding.ItemPlayerBinding
import com.squareup.picasso.Picasso

class AdapterJugadores(val players: Int,val jugador: MutableList<Jugadores>)
    : RecyclerView.Adapter<AdapterJugadores.TextoViewHolder>() {

    class TextoViewHolder(val itemBinding: ItemPlayerBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun getItemCount(): Int {
        return players
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextoViewHolder {
        val playerBinding = ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TextoViewHolder(playerBinding)
    }

    override fun onBindViewHolder(holder: TextoViewHolder, position: Int) {
        val jug = jugador[position]
        holder.itemBinding.user.text = jug.nombre
        holder.itemBinding.stack.text = jug.stack.toString()


    }




}
package com.example.servidorapp


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.servidorapp.databinding.ItemUserBinding


class AdapterUsers( var players: Int, var lista: MutableList<Usuarios>, var stack: Int) :
    RecyclerView.Adapter<AdapterUsers.TextoViewHolder>() {

    class TextoViewHolder(var itemBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun getItemCount(): Int {
        return players
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextoViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TextoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TextoViewHolder, position: Int) {
        holder.itemBinding.save.setOnClickListener{
        var nombre =  holder.itemBinding.nomUser.text.toString()
        lista.add(Usuarios(nombre,stack))
        holder.itemBinding.fondo.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
        }
    }
}
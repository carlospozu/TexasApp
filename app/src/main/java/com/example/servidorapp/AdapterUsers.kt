package com.example.servidorapp


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.servidorapp.databinding.ItemUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException


class AdapterUsers(var players: Int, var stack: Int) :
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
        val nombre =  holder.itemBinding.nomUser.text.toString()
           llamada(holder, nombre, stack, position+1)
        }
    }

    fun llamada(holder: TextoViewHolder, usuario: String, stack: Int, position: Int) {
        val client = OkHttpClient()
        val request = Request.Builder()
        request.url("http://10.0.2.2:8084/jugador/${usuario}/${stack}/${position}")
        val call = client.newCall(request.build())
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.toString())
                CoroutineScope(Dispatchers.Main).launch {
                    println("ALGO HA IDO MAL")
                }
            }

            override fun onResponse(call: Call, response: Response) {
                println(response.toString())
                response.body?.let { responseBody ->
                    val body = responseBody.string()
                    println(body)
                    CoroutineScope(Dispatchers.Main).launch {
                        holder.itemBinding.fondo.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
                    }

                }

            }
        })
    }
}
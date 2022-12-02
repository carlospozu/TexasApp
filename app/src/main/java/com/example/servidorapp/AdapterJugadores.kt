package com.example.servidorapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.servidorapp.databinding.ItemPlayerBinding


class AdapterJugadores(private var players: Int, private var jugador: ListaJugadores)
    : RecyclerView.Adapter<AdapterJugadores.TextoViewHolder>() {

    class TextoViewHolder(val itemBinding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun getItemCount(): Int {
        return players
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextoViewHolder {
        val playerBinding =
            ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TextoViewHolder(playerBinding)
    }

    override fun onBindViewHolder(holder: TextoViewHolder, position: Int) {
        holder.itemBinding.user.text = jugador.listaJug[position].nombre
        holder.itemBinding.stack.text = jugador.listaJug[position].stack.toString()
    }
}
     /*   val id = jug.id

        holder.itemBinding.check.setOnClickListener {
            val peticion = "http://10.0.2.2:8084/check/${id}"
            llamada(holder, peticion)
                }

        holder.itemBinding.call.setOnClickListener {
         //   val peticion = "http://10.0.2.2:8084/call/${cantidad}/${bote}"
         //   llamada(holder, peticion)
        }

        holder.itemBinding.raise.setOnClickListener {
          //  val peticion = "http://10.0.2.2:8084/raise/${id}/${cantidad}/${bote}"
           // llamada(holder, peticion)
        }

        holder.itemBinding.fold.setOnClickListener {
            val peticion = "http://10.0.2.2:8084/fold/${id}"
            llamada(holder, peticion)
        }

        }

    fun llamada(holder: TextoViewHolder, peticion:  String) {
        val client = OkHttpClient()
        val request = Request.Builder()
        request.url("$peticion")
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
                        holder.itemBinding.caja.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
                    }

                }

            }
        })
    }
    */






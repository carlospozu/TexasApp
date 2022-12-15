package com.example.servidorapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

import com.example.servidorapp.databinding.ItemPlayerBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

    private lateinit var juegoActivity: JuegoActivity

class AdapterJugadores(val players: Int, val jugador: ListaJugadores)
    : RecyclerView.Adapter<AdapterJugadores.TextoViewHolder>() {

    private var canToCall = 0
    private var cont = 0
    private var boteActual = 0
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
        var player = players -1


        for (i in 0 .. player){
            if (jugador.listaJug[i].vivo){
                cont ++
            }
        }
        if (cont == 1 ){
            for (i in 0 .. player){
                jugador.listaJug[i].turno = false
                if (jugador.listaJug[i].vivo){
                    Toast.makeText(holder.itemBinding.root.context, "Turno acabado, ganador el jugador ${jugador.listaJug[i].nombre}", Toast.LENGTH_SHORT).show()
                    jugador.listaJug[i].stack += boteActual
                    boteActual = 0
                    jugador.listaJug[0].turno = true
                }
            }
            for (t in 0 .. player){
                jugador.listaJug[t].vivo = true
            }
        }else{
            cont = 0
        }

        val jug= jugador.listaJug[position]
        var next=position+1
        holder.itemBinding.user.text = jug.nombre
        holder.itemBinding.stack.text = jug.stack.toString()

        if (jug.turno){
            holder.itemBinding.check.isEnabled = true
            holder.itemBinding.fold.isEnabled = true
            holder.itemBinding.raise.isEnabled = true
            holder.itemBinding.call.isEnabled = true
            holder.itemBinding.cajaJug.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green))
        }else{
            holder.itemBinding.check.isEnabled = false
            holder.itemBinding.fold.isEnabled = false
            holder.itemBinding.raise.isEnabled = false
            holder.itemBinding.call.isEnabled = false
            holder.itemBinding.cajaJug.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
        }
        val id = jug.id

        holder.itemBinding.foto.setOnClickListener{
            for (i in 0 .. player){
                jugador.listaJug[i].vivo = false
            }
            jug.vivo=true
        }



        holder.itemBinding.check.setOnClickListener {
            val peticion = "http://10.0.2.2:8084/check/${id}"
            llamada(holder, peticion)
            holder.itemBinding.cajaJug.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
            jug.turno = false
            /*
            if (next > players-1){
                next = 0
            }
           jugador.listaJug[next].turno=true

             */
            var bool = false
            while (!bool){
                if (next > players-1){
                    next = 0
                }
                if (jugador.listaJug[next].vivo){
                    jugador.listaJug[next].turno= true
                    next=position+1
                    bool = true
                }else{
                    next ++
                }
            }
            notifyDataSetChanged()
                }


        holder.itemBinding.call.setOnClickListener {
           val peticion = "http://10.0.2.2:8084/call/${id}/${canToCall}/${boteActual}"
            llamada(holder, peticion)
            boteActual += canToCall
            jug.stack = jug.stack - canToCall
            holder.itemBinding.cajaJug.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
            jug.turno = false
           /* if (next > players-1){
                next = 0
            }
            jugador.listaJug[next].turno=true
            */
            //juegoActivity.subirBote(canToCall)
            var bool = false
            while (!bool){
                if (next > players-1){
                    next = 0
                }
                if (jugador.listaJug[next].vivo){
                    jugador.listaJug[next].turno= true
                    next=position+1
                    bool = true
                }else{
                    next ++
                }
            }
           notifyDataSetChanged()
           // notifyItemChanged(position)
        }



        holder.itemBinding.raise.setOnClickListener {
            val subida = holder.itemBinding.subir.text.toString().toInt()
            canToCall = subida
            val peticion = "http://10.0.2.2:8084/raise/${id}/${subida}/${boteActual}"
            llamada(holder, peticion)
            boteActual += canToCall
            jug.stack = jug.stack - canToCall
            holder.itemBinding.cajaJug.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
            jug.turno = false
            /*if (next > players-1){
                next = 0
            }
            jugador.listaJug[next].turno=true
             */
            //juegoActivity.subirBote(canToCall)
            var bool = false
            while (!bool){
                if (next > players-1){
                    next = 0
                }
                if (jugador.listaJug[next].vivo){
                    jugador.listaJug[next].turno= true
                    next=position+1
                    bool = true
                }else{
                    next ++
                }
            }
           notifyDataSetChanged()
          //  notifyItemChanged(position)
        }



        holder.itemBinding.fold.setOnClickListener {
            val peticion = "http://10.0.2.2:8084/fold/${position+1}"
            llamada(holder, peticion)

            holder.itemBinding.cajaJug.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
            jug.turno = false
            jug.vivo = false
           /* if (next > players-1){
                next = 0
            }
            jugador.listaJug[next].turno=true
             */
            var bool = false
            while (!bool){
                if (next > players-1){
                    next = 0
                }
                if (jugador.listaJug[next].vivo){
                    jugador.listaJug[next].turno= true
                    next=position+1
                    bool = true
                }else{
                    next ++
                }
            }
           notifyDataSetChanged()
           // notifyItemChanged(position)
        }


        }




    fun llamada(holder: TextoViewHolder, peticion: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
        request.url(peticion)
        val call = client.newCall(request.build())
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println(e.toString())
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(holder.itemBinding.root.context, "Algo ha ido mal", Toast.LENGTH_SHORT).show()
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









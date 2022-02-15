package com.example.servidorapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Preguntas (
    var nombre: String,
    var respuesta1: Int,
    var respuesta2: Int,
    var respuesta3: Int,
    var respuesta4: Int,
    var repuestaCorrecta: Int,
    var id: Int
) :Parcelable


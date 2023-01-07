package com.aiglesiaspubill.androidavanzadofinal.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


//PRESENTACION
@Parcelize
data class Location (
        val id: String,
        val longitud: String,
        val latitud: String,
        val dateShow: String
): Parcelable

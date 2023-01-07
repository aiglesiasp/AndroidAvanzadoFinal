package com.aiglesiaspubill.androidavanzadofinal.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//PRESENTACION
@Parcelize
data class Hero (
        val id: String,
        val name: String,
        val photo: String,
        val description: String
) : Parcelable

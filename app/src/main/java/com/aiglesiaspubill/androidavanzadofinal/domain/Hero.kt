package com.aiglesiaspubill.androidavanzadofinal.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

//PRESENTACION
data class Hero (
        val id: String,
        val name: String,
        val photo: String,
)

package com.aiglesiaspubill.androidavanzadofinal.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//MODELO PARA BASES DE DATOS LOCAL
@Entity(tableName = "heroes")
data class HeroLocal (
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean
)
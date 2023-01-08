package com.aiglesiaspubill.androidavanzadofinal.data.remote.response

import com.squareup.moshi.Json

//REMOTO
data class LocationRemote (
    @Json(name = "id") val id: String,
    @Json(name = "longitud") val longitud: String,
    @Json(name = "latitud") val latitud: String,
    @Json(name = "dateShow") val dateShow: String
)
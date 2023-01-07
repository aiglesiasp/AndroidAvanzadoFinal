package com.aiglesiaspubill.androidavanzadofinal.data.remote.response

import com.squareup.moshi.Json

//REMOTO
data class LocationRemote (
    @Json(name = "id") val id: String,
    @Json(name = "longitude") val longitud: String,
    @Json(name = "latitude") val latitud: String,
    @Json(name = "dateShow") val dateShow: String
)
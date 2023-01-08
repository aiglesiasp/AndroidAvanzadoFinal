package com.aiglesiaspubill.androidavanzadofinal.data.utils

import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.LocationRemote
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import com.aiglesiaspubill.androidavanzadofinal.domain.Location


fun generateHerosRemote(): List<HeroRemote> {
    return (0 until 10).map { HeroRemote("ID: $it", "Name: $it", "Prohot: $it", "Description: $it", false) }
}

fun generateHeroRemote(): HeroRemote {
    return HeroRemote("ID: 1", "Name: 2", "Prohot: 3", "Description: 4", false)
}

fun generateHerosLocal(): List<HeroLocal> {
    return (0 until 10).map { HeroLocal("ID: $it", "Name: $it", "Prohot: $it", "Description: $it", false) }
}

fun generateHeros(): List<Hero> {
    return (0 until 10).map { Hero("ID: $it", "Name: $it", "Prohot: $it", "Description: $it", false) }
}

fun generateHero(): List<Hero> {
    return (0 until 1).map { Hero("ID: $it", "Name: $it", "Prohot: $it", "Description: $it", false) }
}

fun generateLocalLocations(): List<Location> {
    return (0 until 10).map { Location("ID: $it", "Longitud: $it", "Latitud: $it", "DateShow: $it") }
}

fun generateRemoteLocations(): List<LocationRemote> {
    return (0 until 10).map { LocationRemote("ID: $it", "Longitud: $it", "Latitud: $it", "DateShow: $it") }
}

package com.aiglesiaspubill.androidavanzadofinal.data.mappers

import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero

class RemoteToPresentationMapper {

    //MAPEAR LISTA DE HEROES DE REMOTO A PRESENTACION
    fun map(heroListRemote: List<HeroRemote>): List<Hero> {
        return  heroListRemote.map { map(it) }
    }

    //MAPEAR UN HEROE DE REMOTO A PRESENTACION
    fun map(heroRemote: HeroRemote): Hero {
        return Hero(heroRemote.id, heroRemote.name, heroRemote.photo)
    }
}

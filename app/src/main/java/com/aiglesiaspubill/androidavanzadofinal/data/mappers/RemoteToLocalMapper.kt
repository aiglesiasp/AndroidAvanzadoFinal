package com.aiglesiaspubill.androidavanzadofinal.data.mappers

import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero

class RemoteToLocalMapper {

    //MAPEAR LISTA DE HEROES DE REMOTO A PRESENTACION
    fun map(heroListRemote: List<HeroRemote>): List<HeroLocal> {
        return  heroListRemote.map { map(it) }
    }

    //MAPEAR UN HEROE DE REMOTO A PRESENTACION
    fun map(heroRemote: HeroRemote): HeroLocal {
        return HeroLocal(heroRemote.id, heroRemote.name, heroRemote.photo, heroRemote.description, heroRemote.favorite)
    }
}

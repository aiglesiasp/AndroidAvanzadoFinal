package com.aiglesiaspubill.androidavanzadofinal.data.mappers

import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero

class LocalToPresentationMapper {

    //MAPEAR LISTA DE HEROES DE REMOTO A PRESENTACION
    fun map(heroListLocal: List<HeroLocal>): List<Hero> {
        return  heroListLocal.map { map(it) }
    }
    //MAPEAR UN HEROE DE REMOTO A PRESENTACION
    fun map(heroLocal: HeroLocal): Hero {
        return Hero(heroLocal.id, heroLocal.name, heroLocal.photo)
    }
}

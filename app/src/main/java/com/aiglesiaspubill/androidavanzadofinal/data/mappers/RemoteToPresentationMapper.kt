package com.aiglesiaspubill.androidavanzadofinal.data.mappers

import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero

class RemoteToPresentationMapper {

    //MAPEAR LISTA DE HEROES DE REMOTO A PRESENTACION
    fun map(heroList: List<HeroRemote>): List<Hero> {
        return  heroList.map { map(it) }
    }

    //MAPEAR UN HEROE DE REMOTO A PRESENTACION
    fun map(hero: HeroRemote): Hero {
        return Hero(hero.id, hero.name, hero.photo)
    }
}

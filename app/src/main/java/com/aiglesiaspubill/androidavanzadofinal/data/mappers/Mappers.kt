package com.aiglesiaspubill.androidavanzadofinal.data.mappers

import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import javax.inject.Inject

class Mappers @Inject constructor() {

    //LOCAL A PRESENTACION
    fun mapLocalToPresentation(heroListLocal: List<HeroLocal>): List<Hero> {
        return  heroListLocal.map { mapLocalToPresentationOneHero(it) }
    }
    fun mapLocalToPresentationOneHero(heroLocal: HeroLocal): Hero {
        return Hero(heroLocal.id, heroLocal.name, heroLocal.photo, heroLocal.description)
    }


    //REMOTO A PRESENTACION
    fun mapRemoteToPresentation(heroListRemote: List<HeroRemote>): List<Hero> {
        return  heroListRemote.map { mapRemoteToPresentationOneHero(it) }
    }
    fun mapRemoteToPresentationOneHero(heroRemote: HeroRemote): Hero {
        return Hero(heroRemote.id, heroRemote.name, heroRemote.photo, heroRemote.description)
    }


    //REMOTO A LOCAL
    fun mapRemoteToLocal(heroListRemote: List<HeroRemote>): List<HeroLocal> {
        return  heroListRemote.map { mapRemoteToLocalOneHero(it) }
    }
    fun mapRemoteToLocalOneHero(heroRemote: HeroRemote): HeroLocal {
        return HeroLocal(heroRemote.id, heroRemote.name, heroRemote.photo, heroRemote.description, heroRemote.favorite)
    }

    //PRESENTACION A LOCAL
    fun mapPresentationToLocal(hero: List<Hero>): List<HeroLocal> {
        return  hero.map { mapPresentationToLocalOneHero(it) }
    }
    fun mapPresentationToLocalOneHero(hero: Hero): HeroLocal {
        return HeroLocal(hero.id, hero.name, hero.photo, "", false)
    }

    //REMOTO A ENTIDAD LIVE STATE

    //LOCAL A ENTIDAD LIVE STATE


}
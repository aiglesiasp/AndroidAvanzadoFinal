package com.aiglesiaspubill.androidavanzadofinal.data.mappers

import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.LocationRemote
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import com.aiglesiaspubill.androidavanzadofinal.domain.Location
import javax.inject.Inject

class Mappers @Inject constructor() {

    fun mapLocalToPresentation(heroListLocal: List<HeroLocal>): List<Hero> {
        return heroListLocal.map { mapLocalToPresentationOneHero(it) }
    }

    fun mapLocalToPresentationOneHero(heroLocal: HeroLocal): Hero {
        return Hero(
            heroLocal.id,
            heroLocal.name,
            heroLocal.photo,
            heroLocal.description,
            heroLocal.favorite
        )
    }

    fun mapPresentationToLocal(hero: List<Hero>): List<HeroLocal> {
        return hero.map { mapPresentationToLocalOneHero(it) }
    }

    fun mapPresentationToLocalOneHero(hero: Hero): HeroLocal {
        return HeroLocal(hero.id, hero.name, hero.photo, hero.description, hero.favorite)
    }

    fun mapRemoteToPresentation(heroListRemote: List<HeroRemote>): List<Hero> {
        return heroListRemote.map { mapRemoteToPresentationOneHero(it) }
    }

    fun mapRemoteToPresentationOneHero(heroRemote: HeroRemote): Hero {
        return Hero(
            heroRemote.id,
            heroRemote.name,
            heroRemote.photo,
            heroRemote.description,
            heroRemote.favorite
        )
    }

    fun mapRemoteToLocal(heroListRemote: List<HeroRemote>): List<HeroLocal> {
        return heroListRemote.map { mapRemoteToLocalOneHero(it) }
    }

    fun mapRemoteToLocalOneHero(heroRemote: HeroRemote): HeroLocal {
        return HeroLocal(
            heroRemote.id,
            heroRemote.name,
            heroRemote.photo,
            heroRemote.description,
            heroRemote.favorite
        )
    }

    fun mapLocalToRemote(heroListLocal: List<HeroLocal>): List<HeroRemote> {
        return heroListLocal.map { mapLocalToRemoteOneHero(it) }
    }

    fun mapLocalToRemoteOneHero(heroLocal: HeroLocal): HeroRemote {
        return HeroRemote(
            heroLocal.id,
            heroLocal.name,
            heroLocal.photo,
            heroLocal.description,
            heroLocal.favorite
        )
    }

    fun mapRemoteLocationsToLocations(remoteLocations: List<LocationRemote>): List<Location> {
        return remoteLocations.map { mapRemoteLocationsToLocationsOneLocation(it) }
    }

    fun mapRemoteLocationsToLocationsOneLocation(location: LocationRemote): Location {
        return Location(location.id, location.longitud, location.latitud, location.dateShow)
    }
}
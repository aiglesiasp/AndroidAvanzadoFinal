package com.aiglesiaspubill.androidavanzadofinal.data.local

import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal

interface LocalDataSource {
    fun getHeroes(): List<HeroLocal>
    fun insertHeros(remoteHero: List<HeroLocal>)
}
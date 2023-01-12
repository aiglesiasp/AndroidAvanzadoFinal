package com.aiglesiaspubill.androidavanzadofinal.fakes

import com.aiglesiaspubill.androidavanzadofinal.data.local.LocalDataSource
import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal
import com.aiglesiaspubill.androidavanzadofinal.utils.generateHerosLocal

class FakeLocalDataSource: LocalDataSource {


    override fun getHeroes(): List<HeroLocal> {
            return generateHerosLocal()
        }

    override fun insertHeros(remoteHero: List<HeroLocal>) {
    }
}
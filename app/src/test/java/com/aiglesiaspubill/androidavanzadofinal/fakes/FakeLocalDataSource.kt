package com.aiglesiaspubill.androidavanzadofinal.fakes

import com.aiglesiaspubill.androidavanzadofinal.data.local.LocalDataSource
import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal
import com.aiglesiaspubill.androidavanzadofinal.utils.generateHerosLocal

class FakeLocalDataSource: LocalDataSource {

    private var firstCall = true

    override fun getHeroes(): List<HeroLocal> {
        if(firstCall) {
            firstCall = false
            return emptyList()
        } else {
            return generateHerosLocal()
        }
    }

    override fun insertHeros(remoteHero: List<HeroLocal>) {
    }
}
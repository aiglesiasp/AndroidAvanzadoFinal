package com.aiglesiaspubill.androidavanzadofinal.data.local

import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: HeroDAO) : LocalDataSource {

    override fun getHeroes(): List<HeroLocal> {
        return dao.getAllHeros()
    }

    override fun insertAll(remoteHero: List<HeroLocal>) {
        dao.insertAll(remoteHero)
    }
}

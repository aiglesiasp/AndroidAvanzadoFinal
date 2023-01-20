package com.aiglesiaspubill.androidavanzadofinal.data.local

import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: HeroDAO) : LocalDataSource {

    //Obtener todos los heroes de la base de datos
    override fun getHeroes(): List<HeroLocal> {
        return dao.getAllHeros()
    }

    //Insertar un heroe en la base de datos
    override fun insertAll(remoteHero: List<HeroLocal>) {
        dao.insertAll(remoteHero)
    }

}

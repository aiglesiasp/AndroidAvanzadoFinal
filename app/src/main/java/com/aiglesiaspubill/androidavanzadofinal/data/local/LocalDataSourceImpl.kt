package com.aiglesiaspubill.androidavanzadofinal.data.local

import android.content.Context
import androidx.room.Room
import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal

class LocalDataSourceImpl(private val dao: HeroDAO) : LocalDataSource {

    //INICIAR BASE DE DATOS
    //override fun initDatabase(context: Context) {
    //    val db = Room.databaseBuilder(context, HeroDatabase::class.java, "database-name").build()
    //    dao = db.getDAO()
    //}



    //Obtener todos los heroes de la base de datos
    override fun getHeroes(): List<HeroLocal> {
        return dao.getAllHeros()
    }

    override fun insertHeros(remoteHero: List<HeroLocal>) {
        dao.insertAll(remoteHero)
    }

}

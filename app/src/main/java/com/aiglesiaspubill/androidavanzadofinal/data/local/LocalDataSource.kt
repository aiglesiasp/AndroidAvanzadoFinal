package com.aiglesiaspubill.androidavanzadofinal.data.local

import android.content.Context
import android.provider.DocumentsContract.Root
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote

class LocalDataSource {

    private lateinit var dao: HeroDAO

    //INICIAR BASE DE DATOS
    fun initDatabase(context: Context) {
        val db = Room.databaseBuilder(context, HeroDatabase::class.java, "database-name").build()
        dao = db.getDAO()
    }

    //Obtener todos los heroes de la base de datos
    fun getHeroes(): List<HeroLocal> {
        return dao.getAllHeros()
    }

    fun insertHeros(remoteHero: List<HeroLocal>) {
        dao.insertAll(remoteHero)
    }

}

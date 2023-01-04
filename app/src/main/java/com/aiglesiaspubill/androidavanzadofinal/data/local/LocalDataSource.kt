package com.aiglesiaspubill.androidavanzadofinal.data.local

import android.content.Context
import android.provider.DocumentsContract.Root
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class LocalDataSource {

    private lateinit var dao: HeroDAO

    //INICIAR BASE DE DATOS
    fun initDatabase(context: Context) {
        val db = Room.databaseBuilder(
            context,
            HeroDatabase::class.java, "database-name"
        ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.d("ROOM", "Base de datos creada")
                }
            })
            .build()

        dao = db.getDAO()

    }

}

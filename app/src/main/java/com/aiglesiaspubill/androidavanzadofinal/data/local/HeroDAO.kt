package com.aiglesiaspubill.androidavanzadofinal.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal

@Dao
interface HeroDAO {
    //SELECCIONAR TODOS LOS HEROES
    @Query("SELECT * FROM heroes")
    fun getAllHeros() : List<HeroLocal>

    //INSERTAR LISTA HEROES
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(hero: List<HeroLocal>)

    //INSERTAR UN HEROE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHero(hero: HeroLocal)
}
package com.aiglesiaspubill.androidavanzadofinal.data.local

import androidx.room.*
import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal

@Dao
interface HeroDAO {
    //SELECCIONAR TODOS LOS HEROES
    @Query("SELECT * FROM heroes")
    fun getAllHeros(): List<HeroLocal>

    //INSERTAR LISTA HEROES
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(hero: List<HeroLocal>)

}
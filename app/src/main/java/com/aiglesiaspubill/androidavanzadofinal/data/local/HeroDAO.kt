package com.aiglesiaspubill.androidavanzadofinal.data.local

import androidx.room.*
import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal

@Dao
interface HeroDAO {

    @Query("SELECT * FROM heroes")
    fun getAllHeros(): List<HeroLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(hero: List<HeroLocal>)

}
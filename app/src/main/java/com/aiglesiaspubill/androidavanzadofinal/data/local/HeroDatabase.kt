package com.aiglesiaspubill.androidavanzadofinal.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal


@Database(entities = [HeroLocal::class], version = 1)
abstract class HeroDatabase: RoomDatabase() {
    abstract fun getDAO(): HeroDAO
}
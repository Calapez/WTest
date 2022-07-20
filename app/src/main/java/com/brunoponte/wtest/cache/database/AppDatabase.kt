package com.brunoponte.wtest.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brunoponte.wtest.cache.daos.PostalCodeDao
import com.brunoponte.wtest.cache.entities.PostalCodeEntity

@Database(
    entities = [PostalCodeEntity::class],
    version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun postalCodeDao(): PostalCodeDao

    companion object{
        const val DATABASE_NAME = "wtest_repos_db"
    }

}
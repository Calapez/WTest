package com.brunoponte.wtest.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brunoponte.wtest.cache.daos.article.ArticleDao
import com.brunoponte.wtest.cache.daos.postalCode.PostalCodeDao
import com.brunoponte.wtest.cache.entities.article.ArticleEntity
import com.brunoponte.wtest.cache.entities.postalCode.PostalCodeEntity

@Database(
    entities = [PostalCodeEntity::class, ArticleEntity::class],
    version = 4)
abstract class AppDatabase: RoomDatabase() {

    abstract fun postalCodeDao(): PostalCodeDao

    abstract fun articleDao(): ArticleDao

    companion object{
        const val DATABASE_NAME = "wtest_repos_db"
    }

}
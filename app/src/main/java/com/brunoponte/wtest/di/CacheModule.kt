package com.brunoponte.wtest.di

import androidx.room.Room
import com.brunoponte.wtest.App
import com.brunoponte.wtest.cache.daos.PostalCodeDao
import com.brunoponte.wtest.cache.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideAppDatabase(app: App): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePostalCodeDao(db: AppDatabase): PostalCodeDao {
        return db.postalCodeDao()
    }

}
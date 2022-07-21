package com.brunoponte.wtest.di

import com.brunoponte.wtest.cache.daos.PostalCodeDao
import com.brunoponte.wtest.network.IRequestService
import com.brunoponte.wtest.repository.IPostalCodeRepository
import com.brunoponte.wtest.repository.PostalCodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepoRepository(postalCodeDao: PostalCodeDao) : IPostalCodeRepository {
        return PostalCodeRepository(postalCodeDao)
    }
}
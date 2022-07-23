package com.brunoponte.wtest.di

import com.brunoponte.wtest.cache.daos.article.ArticleDao
import com.brunoponte.wtest.cache.daos.postalCode.PostalCodeDao
import com.brunoponte.wtest.network.IRequestService
import com.brunoponte.wtest.repository.article.ArticleRepository
import com.brunoponte.wtest.repository.article.IArticleRepository
import com.brunoponte.wtest.repository.postalCode.IPostalCodeRepository
import com.brunoponte.wtest.repository.postalCode.PostalCodeRepository
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
    fun providePostalCodeRepository(postalCodeDao: PostalCodeDao) : IPostalCodeRepository {
        return PostalCodeRepository(postalCodeDao)
    }

    @Singleton
    @Provides
    fun provideArticleRepository(articleDao: ArticleDao, requestService: IRequestService) : IArticleRepository {
        return ArticleRepository(articleDao, requestService)
    }
}
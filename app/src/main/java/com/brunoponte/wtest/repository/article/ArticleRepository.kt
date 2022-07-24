package com.brunoponte.wtest.repository.article

import com.brunoponte.wtest.cache.daos.article.ArticleDao
import com.brunoponte.wtest.cache.entities.article.ArticleEntityMapper
import com.brunoponte.wtest.domainModels.Article
import com.brunoponte.wtest.network.IRequestService
import com.brunoponte.wtest.network.dtos.article.ArticleDtoMapper

class ArticleRepository(
    private val articleDao: ArticleDao,
    private val requestService: IRequestService
) : IArticleRepository {

    override suspend fun getArticles(pageSize: Int, page: Int) : List<Article> {

        try {
            val articles = getArticlesFromNetwork(pageSize, page)

            // Insert into cache
            articleDao.insertArticles(ArticleEntityMapper.toEntityList(articles))
        } catch (e: Exception) {
            // There was an issue
            e.printStackTrace()
        }

        // Always returns the Articles stored in the cache
        val cachedArticles = articleDao.getArticles(pageSize = pageSize, page = page)

        return ArticleEntityMapper.toDomainModelList(cachedArticles)
    }

    override suspend fun getArticleById(articleId: String) : Article? {
        val articleEntity = articleDao.getArticleById(articleId)
        return articleEntity?.let { ArticleEntityMapper.mapToDomainModel(it) }
    }

    private suspend fun getArticlesFromNetwork(pageSize: Int, page: Int) =
        ArticleDtoMapper.toDomainModelList(
            requestService.getArticles(pageSize, page)
        )
}
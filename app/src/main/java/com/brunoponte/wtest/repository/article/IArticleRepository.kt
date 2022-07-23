package com.brunoponte.wtest.repository.article

import com.brunoponte.wtest.domainModels.Article

interface IArticleRepository {
    suspend fun getArticles(pageSize: Int, page: Int) : List<Article>

    suspend fun getArticleById(articleId: Long) : Article?
}
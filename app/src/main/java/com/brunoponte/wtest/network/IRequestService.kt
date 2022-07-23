package com.brunoponte.wtest.network

import com.brunoponte.wtest.network.dtos.article.ArticleDto
import com.brunoponte.wtest.network.dtos.article.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IRequestService {

    @GET("articles")
    suspend fun getArticles(
        @Query("limit") pageSize: Int,
        @Query("page") page: Int,
    ) : ArticlesResponse

}
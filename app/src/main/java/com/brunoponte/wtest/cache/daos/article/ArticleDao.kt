package com.brunoponte.wtest.cache.daos.article

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brunoponte.wtest.cache.entities.article.ArticleEntity

@Dao
interface ArticleDao {

    @Query("""
        SELECT * FROM articles 
        LIMIT :pageSize 
        OFFSET (:pageSize * (:page - 1))
    """)
    suspend fun getArticles(pageSize: Int, page: Int): List<ArticleEntity>

    @Query("SELECT * FROM articles WHERE id = :articleId")
    suspend fun getArticleById(articleId: String): ArticleEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>): LongArray

    @Query("DELETE FROM articles")
    suspend fun nukeTable()
}
package com.brunoponte.wtest.cache.entities.article

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "publish_date")
    var publishDate: String?,

    @ColumnInfo(name = "hero_url")
    var heroUrl: String?,

    @ColumnInfo(name = "author")
    val author: String?,

    @ColumnInfo(name = "summary")
    val summary: String?,

    @ColumnInfo(name = "body")
    val body: String?,

)
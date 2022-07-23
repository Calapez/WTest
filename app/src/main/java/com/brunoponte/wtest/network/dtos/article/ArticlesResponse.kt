package com.brunoponte.wtest.network.dtos.article

import com.google.gson.annotations.SerializedName

data class ArticlesResponse (

    @SerializedName("items")
    val articles: List<ArticleDto>,
)
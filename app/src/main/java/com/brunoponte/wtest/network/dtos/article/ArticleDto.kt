package com.brunoponte.wtest.network.dtos.article

import com.google.gson.annotations.SerializedName

data class ArticleDto (

    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String?,

    @SerializedName("published-at")
    val publishDate: String?,

    @SerializedName("hero")
    val heroUrl: String?,

    @SerializedName("author")
    val author: String?,

    @SerializedName("avatar")
    val avatarUrl: String?,

    @SerializedName("summary")
    val summary: String?,

    @SerializedName("body")
    val body: String?,
)
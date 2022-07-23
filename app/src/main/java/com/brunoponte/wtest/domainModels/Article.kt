package com.brunoponte.wtest.domainModels


data class Article (
    val id: String,
    val title: String?,
    val publishDate: String?,
    val heroUrl: String?,
    val author: String?,
    val avatarUrl: String?,
    val summary: String?,
    val body: String?,
)
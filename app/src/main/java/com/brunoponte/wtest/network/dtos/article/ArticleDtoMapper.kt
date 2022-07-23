package com.brunoponte.wtest.network.dtos.article

import com.brunoponte.wtest.domainModels.Article

class ArticleDtoMapper {

    companion object {

        fun mapToDomainModel(dto: ArticleDto) = Article (
            id = dto.id,
            title = dto.title,
            publishDate = dto.publishDate,
            heroUrl = dto.heroUrl,
            author = dto.author,
            avatarUrl = dto.avatarUrl,
            summary = dto.summary,
            body = dto.body
        )

        fun toDomainModelList(dtoList: List<ArticleDto>) = dtoList.map { dto ->
            mapToDomainModel(dto)
        }

    }

}
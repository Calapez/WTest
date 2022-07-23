package com.brunoponte.wtest.cache.entities.article

import com.brunoponte.wtest.domainModels.Article

class ArticleEntityMapper {

    companion object {

        fun mapToDomainModel(entity: ArticleEntity) = Article (
            id = entity.id,
            title = entity.title,
            publishDate = entity.publishDate,
            heroUrl = entity.heroUrl,
            author = entity.author,
            summary = entity.summary,
            body = entity.body
        )

        fun mapToEntity(model: Article) = ArticleEntity (
            id = model.id,
            title = model.title,
            publishDate = model.publishDate,
            heroUrl = model.heroUrl,
            author = model.author,
            summary = model.summary,
            body = model.body
        )

        fun toEntityList(domainModelList: List<Article>) = domainModelList.map { domainModel ->
            mapToEntity(domainModel)
        }

        fun toDomainModelList(entityList: List<ArticleEntity>) = entityList.map { entity ->
            mapToDomainModel(entity)
        }

    }

}
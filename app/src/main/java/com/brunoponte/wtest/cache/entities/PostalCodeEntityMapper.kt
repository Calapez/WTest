package com.brunoponte.wtest.cache.entities

import com.brunoponte.wtest.domainModels.PostalCode

class PostalCodeEntityMapper {

    companion object {

        fun mapToEntity(domainModel: PostalCode) = PostalCodeEntity (
            number = domainModel.number,
            extension = domainModel.extension,
            designation = domainModel.designation,
        )

        fun mapToDomainModel(entity: PostalCodeEntity) = PostalCode (
            number = entity.number,
            extension = entity.extension,
            designation = entity.designation,
        )

        fun toEntityList(domainModelList: List<PostalCode>) = domainModelList.map { domainModel ->
            mapToEntity(domainModel)
        }

        fun toDomainModelList(entityList: List<PostalCodeEntity>) = entityList.map { entity ->
            mapToDomainModel(entity)
        }

    }

}
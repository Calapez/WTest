package com.brunoponte.wtest.cache.entities.postalCode

import com.brunoponte.wtest.domainModels.PostalCode

class PostalCodeEntityMapper {

    companion object {

        fun mapToEntity(domainModel: PostalCode) = PostalCodeEntity (
            code = domainModel.code,
            designation = domainModel.designation,
        )

        fun mapToDomainModel(entity: PostalCodeEntity) = PostalCode (
            code = entity.code,
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
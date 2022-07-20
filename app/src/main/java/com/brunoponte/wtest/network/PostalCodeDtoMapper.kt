package com.brunoponte.wtest.network

import com.brunoponte.wtest.domainModels.PostalCode

class PostalCodeDtoMapper {

    companion object {

        fun mapToDomainModel(dto: PostalCodeDto) = PostalCode (
            number = dto.number,
            extension = dto.extension,
            designation = dto.designation,
        )

        fun toDomainModelList(dtoList: List<PostalCodeDto>) = dtoList.map { dto ->
            mapToDomainModel(dto)
        }

    }

}
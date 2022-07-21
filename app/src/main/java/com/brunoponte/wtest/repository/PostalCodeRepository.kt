package com.brunoponte.wtest.repository

import com.brunoponte.wtest.cache.daos.PostalCodeDao
import com.brunoponte.wtest.cache.entities.PostalCodeEntityMapper
import com.brunoponte.wtest.domainModels.PostalCode
import com.brunoponte.wtest.helpers.Util
import com.brunoponte.wtest.network.IRequestService
import com.brunoponte.wtest.network.PostalCodeDto
import com.brunoponte.wtest.network.PostalCodeDtoMapper
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.net.URL

class PostalCodeRepository(
    private val postalCodeDao: PostalCodeDao
) : IPostalCodeRepository {

    override suspend fun fetchPostalCodes() {
        if (postalCodeDao.countPostalCodes() > 0) {
            // Postal Codes already in cache, don't fetch in network.
            return
        }

        try {
            val postalCodes = getPostalCodesFromNetwork()

            // Insert into cache
            postalCodeDao.insertPostalCodes(PostalCodeEntityMapper.toEntityList(postalCodes))
        } catch (e: Exception) {
            // There was an issue
            e.printStackTrace()
        }
    }

    override suspend fun searchPostalCodes(pageSize: Int, page: Int, query: String) : List<PostalCode> {
        try {
            val tokens = query.split(" ")
            val x = PostalCodeEntityMapper.toDomainModelList(postalCodeDao.searchPostalCodes(pageSize, page, query))
            return x
        } catch (e: Exception) {
            return listOf()
        }
    }

    private suspend fun getPostalCodesFromNetwork() : List<PostalCode> {
        val postalCodesListJsonStr = URL(Util.postalCodesEndpointUrl).readText()
        val csvParser = CSVParser(postalCodesListJsonStr.reader(), CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withTrim()
            .withDelimiter(','))

        val postalCodeDtos = mutableListOf<PostalCodeDto>()
        for (csvRecord in csvParser) {
            val a = csvRecord.get("num_cod_postal")
            val b = csvRecord.get("ext_cod_postal")
            val c = csvRecord.get("desig_postal")
            if (a != null && b != null && c != null) {
                postalCodeDtos.add(PostalCodeDto(a,b,c))
            }
        }

        return PostalCodeDtoMapper.toDomainModelList(postalCodeDtos)
    }

}
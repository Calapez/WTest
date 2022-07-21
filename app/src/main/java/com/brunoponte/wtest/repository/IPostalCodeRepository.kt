package com.brunoponte.wtest.repository

import com.brunoponte.wtest.domainModels.PostalCode

interface IPostalCodeRepository {
    suspend fun fetchPostalCodes()

    suspend fun searchPostalCodes(pageSize: Int, page: Int, query: String) : List<PostalCode>
}
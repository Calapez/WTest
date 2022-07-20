package com.brunoponte.wtest.repository

import com.brunoponte.wtest.domainModels.PostalCode

interface IPostalCodeRepository {
    suspend fun fetchPostalCodes()

    suspend fun searchPostalCodes(query: String) : List<PostalCode>
}
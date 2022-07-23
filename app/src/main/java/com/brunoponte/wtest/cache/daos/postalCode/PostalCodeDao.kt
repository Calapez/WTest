package com.brunoponte.wtest.cache.daos.postalCode

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brunoponte.wtest.cache.entities.postalCode.PostalCodeEntity

@Dao
interface PostalCodeDao {

    @Query("""
        SELECT * FROM postal_codes 
        WHERE code LIKE '%' || :query || '%'  OR designation LIKE '%' || :query || '%'
        LIMIT :pageSize 
        OFFSET (:pageSize * (:page - 1))""")
    suspend fun searchPostalCodes(pageSize: Int, page: Int, query: String): List<PostalCodeEntity>

    @Query("SELECT COUNT(*) FROM postal_codes")
    suspend fun countPostalCodes(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostalCodes(postalCodes: List<PostalCodeEntity>): LongArray

    @Query("DELETE FROM postal_codes")
    suspend fun nukeTable()
}
package com.brunoponte.wtest.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brunoponte.wtest.cache.entities.PostalCodeEntity

@Dao
interface PostalCodeDao {

    @Query("SELECT * FROM postal_codes")
    suspend fun getPostalCodes(): List<PostalCodeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostalCodes(postalCodes: List<PostalCodeEntity>): LongArray

    @Query("DELETE FROM postal_codes")
    suspend fun nukeTable()
}
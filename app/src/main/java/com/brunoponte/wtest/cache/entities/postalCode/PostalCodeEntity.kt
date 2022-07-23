package com.brunoponte.wtest.cache.entities.postalCode

import androidx.room.Entity

@Entity(tableName = "postal_codes", primaryKeys = ["code", "designation"])
data class PostalCodeEntity(
    val code: String,
    val designation: String,
)
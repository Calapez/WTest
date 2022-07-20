package com.brunoponte.wtest.cache.entities

import androidx.room.Entity

@Entity(tableName = "postal_codes", primaryKeys = ["number","extension","designation"])
data class PostalCodeEntity(
    val number: String,
    val extension: String,
    val designation: String,
)
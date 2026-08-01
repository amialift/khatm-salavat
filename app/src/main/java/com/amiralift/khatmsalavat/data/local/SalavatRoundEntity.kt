package com.amiralift.khatmsalavat.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "salavat_rounds")
data class SalavatRoundEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val createdAt: Long = System.currentTimeMillis()
)
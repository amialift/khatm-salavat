package com.amiralift.khatmsalavat.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quran_parts")
data class QuranPartEntity(

    @PrimaryKey
    val partNumber: Int,

    val name: String = ""

)
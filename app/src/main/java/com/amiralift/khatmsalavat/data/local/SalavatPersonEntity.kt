package com.amiralift.khatmsalavat.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "salavat_people",
    foreignKeys = [
        ForeignKey(
            entity = SalavatRoundEntity::class,
            parentColumns = ["id"],
            childColumns = ["roundId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["roundId", "slotNumber"], unique = true)
    ]
)
data class SalavatPersonEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val roundId: Int,

    val slotNumber: Int,

    val name: String = ""
)
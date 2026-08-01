package com.amiralift.khatmsalavat.data.local

import androidx.room.Embedded
import androidx.room.Relation

data class SalavatRoundWithPeople(

    @Embedded
    val round: SalavatRoundEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "roundId"
    )
    val people: List<SalavatPersonEntity>

)
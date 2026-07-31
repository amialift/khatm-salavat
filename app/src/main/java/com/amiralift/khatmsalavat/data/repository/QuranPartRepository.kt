package com.amiralift.khatmsalavat.data.repository

import com.amiralift.khatmsalavat.data.local.QuranPartDao
import com.amiralift.khatmsalavat.data.local.QuranPartEntity
import kotlinx.coroutines.flow.Flow


class QuranPartRepository(
    private val dao: QuranPartDao
) {


    val parts: Flow<List<QuranPartEntity>> =
        dao.getAllParts()


    suspend fun updatePart(
        partNumber: Int,
        name: String
    ) {

        dao.updateName(
            partNumber,
            name
        )

    }


    suspend fun insertAll(
        parts: List<QuranPartEntity>
    ) {

        dao.insertAll(
            parts
        )

    }

    suspend fun getPartsOnce(): List<QuranPartEntity> {
        return dao.getAllPartsOnce()
    }

}
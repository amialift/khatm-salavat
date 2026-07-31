package com.amiralift.khatmsalavat.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface QuranPartDao {


    @Query("SELECT * FROM quran_parts ORDER BY partNumber ASC")
    fun getAllParts(): Flow<List<QuranPartEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(
        parts: List<QuranPartEntity>
    )


    @Query(
        "UPDATE quran_parts SET name = :name WHERE partNumber = :partNumber"
    )
    suspend fun updateName(
        partNumber: Int,
        name: String
    )


    @Query("SELECT * FROM quran_parts ORDER BY partNumber ASC")
    suspend fun getAllPartsOnce(): List<QuranPartEntity>

}
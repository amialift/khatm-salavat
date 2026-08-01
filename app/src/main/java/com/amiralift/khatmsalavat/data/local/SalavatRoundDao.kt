package com.amiralift.khatmsalavat.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SalavatRoundDao {


    @Query("SELECT * FROM salavat_rounds ORDER BY id")
    fun getAllRounds(): Flow<List<SalavatRoundEntity>>


    @Query("SELECT * FROM salavat_rounds ORDER BY id")
    suspend fun getAllRoundsOnce(): List<SalavatRoundEntity>


    @Insert
    suspend fun insertRound(
        round: SalavatRoundEntity
    ): Long


    @Delete
    suspend fun deleteRound(
        round: SalavatRoundEntity
    )

}
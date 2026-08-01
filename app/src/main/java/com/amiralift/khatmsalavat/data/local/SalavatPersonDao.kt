package com.amiralift.khatmsalavat.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SalavatPersonDao {


    @Query("""
        SELECT * FROM salavat_people
        WHERE roundId = :roundId
        ORDER BY slotNumber
    """)
    suspend fun getPeopleByRound(
        roundId: Int
    ): List<SalavatPersonEntity>



    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertPerson(
        person: SalavatPersonEntity
    )



    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertPeople(
        people: List<SalavatPersonEntity>
    )



    @Query("""
        UPDATE salavat_people
        SET name = :name
        WHERE roundId = :roundId
        AND slotNumber = :slotNumber
    """)
    suspend fun updatePersonName(
        roundId: Int,
        slotNumber: Int,
        name: String
    )



    @Query("""
        DELETE FROM salavat_people
        WHERE roundId = :roundId
    """)
    suspend fun deletePeopleByRound(
        roundId: Int
    )

}
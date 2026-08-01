package com.amiralift.khatmsalavat.data.repository

import com.amiralift.khatmsalavat.data.local.SalavatPersonDao
import com.amiralift.khatmsalavat.data.local.SalavatPersonEntity
import com.amiralift.khatmsalavat.data.local.SalavatRoundDao
import com.amiralift.khatmsalavat.data.local.SalavatRoundEntity
import kotlinx.coroutines.flow.Flow


class SalavatRepository(

    private val roundDao: SalavatRoundDao,

    private val personDao: SalavatPersonDao

) {


    val rounds: Flow<List<SalavatRoundEntity>> =
        roundDao.getAllRounds()



    suspend fun createRound() {

        val roundId = roundDao.insertRound(
            SalavatRoundEntity()
        )


        val people = (1..14).map {

            SalavatPersonEntity(

                roundId = roundId.toInt(),

                slotNumber = it,

                name = ""

            )

        }


        personDao.insertPeople(
            people
        )

    }



    suspend fun deleteRound(
        round: SalavatRoundEntity
    ) {

        roundDao.deleteRound(
            round
        )

    }



    suspend fun getPeopleByRound(
        roundId: Int
    ): List<SalavatPersonEntity> {


        return personDao.getPeopleByRound(
            roundId
        )

    }



    suspend fun updatePersonName(
        roundId: Int,
        slotNumber: Int,
        name: String
    ) {


        personDao.updatePersonName(

            roundId,

            slotNumber,

            name

        )

    }



    suspend fun savePeople(
        people: List<SalavatPersonEntity>
    ) {

        personDao.insertPeople(
            people
        )

    }



    suspend fun getAllRoundsOnce(): List<SalavatRoundEntity> {

        return roundDao.getAllRoundsOnce()

    }



    /**
     * دریافت شماره نمایشی دور
     *
     * مثال:
     * id های موجود:
     * 2 , 5 , 9
     *
     * roundId = 5
     *
     * نتیجه:
     * دور دوم
     */
    suspend fun getRoundDisplayNumber(
        roundId: Int
    ): Int {


        val allRounds =
            roundDao.getAllRoundsOnce()


        val index =
            allRounds.indexOfFirst {

                it.id == roundId

            }


        return if (index >= 0) {

            index + 1

        } else {

            0

        }

    }

}
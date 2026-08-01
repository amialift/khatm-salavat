package com.amiralift.khatmsalavat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amiralift.khatmsalavat.data.local.SalavatPersonEntity
import com.amiralift.khatmsalavat.data.local.SalavatRoundEntity
import com.amiralift.khatmsalavat.data.repository.SalavatRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class SalavatViewModel(
    private val repository: SalavatRepository
) : ViewModel() {


    val rounds = repository.rounds
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )



    fun addRound() {

        viewModelScope.launch {

            repository.createRound()

        }

    }



    fun deleteRound(
        round: SalavatRoundEntity
    ) {

        viewModelScope.launch {

            repository.deleteRound(
                round
            )

        }

    }



    fun getPeople(
        roundId: Int,
        onResult: (List<SalavatPersonEntity>) -> Unit
    ) {

        viewModelScope.launch {

            val people =
                repository.getPeopleByRound(
                    roundId
                )


            onResult(
                people
            )

        }

    }



    fun savePeople(
        people: List<SalavatPersonEntity>
    ) {

        viewModelScope.launch {

            repository.savePeople(
                people
            )

        }

    }



    fun updatePersonName(
        roundId: Int,
        slotNumber: Int,
        name: String
    ) {

        viewModelScope.launch {

            repository.updatePersonName(
                roundId,
                slotNumber,
                name
            )

        }

    }



    fun getRoundDisplayNumber(
        roundId: Int,
        onResult: (Int) -> Unit
    ) {

        viewModelScope.launch {

            val number =
                repository.getRoundDisplayNumber(
                    roundId
                )


            onResult(
                number
            )

        }

    }

}
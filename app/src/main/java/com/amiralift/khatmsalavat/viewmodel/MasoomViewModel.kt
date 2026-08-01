package com.amiralift.khatmsalavat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amiralift.khatmsalavat.data.model.SalavatRoundResult
import com.amiralift.khatmsalavat.data.repository.SalavatRepository
import com.amiralift.khatmsalavat.domain.salavat.SalavatLottery
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MasoomViewModel(
    private val repository: SalavatRepository
) : ViewModel() {

    private val _results =
        MutableStateFlow<List<SalavatRoundResult>>(emptyList())

    val results =
        _results.asStateFlow()

    fun drawAllRounds() {

        viewModelScope.launch {

            val rounds =
                repository.getAllRoundsOnce()

            val allResults =
                rounds.mapIndexed { index, round ->

                    val people =
                        repository.getPeopleByRound(
                            round.id
                        )

                    SalavatRoundResult(

                        roundNumber = index + 1,

                        results =
                            SalavatLottery.draw(
                                people
                            )

                    )

                }

            _results.value =
                allResults

        }

    }

}
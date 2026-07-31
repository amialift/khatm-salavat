package com.amiralift.khatmsalavat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amiralift.khatmsalavat.data.local.QuranPartEntity
import com.amiralift.khatmsalavat.data.preferences.UserPreferences
import com.amiralift.khatmsalavat.data.repository.QuranPartRepository
import com.amiralift.khatmsalavat.domain.quran.QuranRoundDistributor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class QuranViewModel(
    private val repository: QuranPartRepository,
    private val preferences: UserPreferences
) : ViewModel() {


    private val _result =
        MutableStateFlow<List<QuranPartEntity>>(emptyList())


    val result =
        _result.asStateFlow()



    fun calculate(
        round: Int
    ) {

        viewModelScope.launch {


            val parts =
                repository.getPartsOnce()


            _result.value =
                QuranRoundDistributor.distribute(
                    parts,
                    round
                )


            preferences.saveLastRound(
                round
            )

        }

    }

}
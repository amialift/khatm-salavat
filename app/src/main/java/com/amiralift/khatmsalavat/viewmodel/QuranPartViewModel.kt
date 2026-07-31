package com.amiralift.khatmsalavat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amiralift.khatmsalavat.data.local.QuranPartEntity
import com.amiralift.khatmsalavat.data.repository.QuranPartRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class QuranPartViewModel(
    private val repository: QuranPartRepository
) : ViewModel() {


    val parts = repository.parts
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )


    fun initializeParts() {

        viewModelScope.launch {

            val currentParts = repository.getPartsOnce()

            if (currentParts.isEmpty()) {

                val list = (1..30).map {

                    QuranPartEntity(
                        partNumber = it,
                        name = ""
                    )

                }

                repository.insertAll(
                    list
                )
            }
        }
    }


    fun updatePart(
        partNumber: Int,
        name: String
    ) {

        viewModelScope.launch {

            repository.updatePart(
                partNumber,
                name
            )

        }

    }

}
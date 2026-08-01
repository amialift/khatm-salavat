package com.amiralift.khatmsalavat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amiralift.khatmsalavat.data.repository.SalavatRepository

class SalavatViewModelFactory(
    private val repository: SalavatRepository
) : ViewModelProvider.Factory {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(
                SalavatViewModel::class.java
            )
        ) {

            return SalavatViewModel(
                repository
            ) as T

        }


        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )

    }

}
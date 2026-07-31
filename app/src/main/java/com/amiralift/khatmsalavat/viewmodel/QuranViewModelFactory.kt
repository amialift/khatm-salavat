package com.amiralift.khatmsalavat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amiralift.khatmsalavat.data.preferences.UserPreferences
import com.amiralift.khatmsalavat.data.repository.QuranPartRepository


class QuranViewModelFactory(
    private val repository: QuranPartRepository,
    private val preferences: UserPreferences
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {


        if (modelClass.isAssignableFrom(
                QuranViewModel::class.java
            )
        ) {


            return QuranViewModel(
                repository,
                preferences
            ) as T


        }


        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )

    }

}
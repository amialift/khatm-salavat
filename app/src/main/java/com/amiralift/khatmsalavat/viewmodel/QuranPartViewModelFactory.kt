package com.amiralift.khatmsalavat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amiralift.khatmsalavat.data.repository.QuranPartRepository

class QuranPartViewModelFactory(
    private val repository: QuranPartRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(QuranPartViewModel::class.java)) {
            return QuranPartViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
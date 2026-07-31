package com.amiralift.khatmsalavat.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(
    name = "user_preferences"
)


class UserPreferences(
    private val context: Context
) {


    companion object {

        private val LAST_ROUND =
            intPreferencesKey("last_round")

    }


    val lastRound: Flow<Int> =
        context.dataStore.data.map { preferences ->

            preferences[LAST_ROUND] ?: 1

        }


    suspend fun saveLastRound(
        round: Int
    ) {

        context.dataStore.edit { preferences ->

            preferences[LAST_ROUND] = round

        }

    }


}
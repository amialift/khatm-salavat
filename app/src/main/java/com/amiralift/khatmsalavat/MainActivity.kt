package com.amiralift.khatmsalavat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.amiralift.khatmsalavat.data.local.DatabaseProvider
import com.amiralift.khatmsalavat.data.repository.SalavatRepository
import com.amiralift.khatmsalavat.navigation.AppNavigation
import com.amiralift.khatmsalavat.ui.theme.KhatmSalavatTheme
import com.amiralift.khatmsalavat.viewmodel.SalavatViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()


        val database = DatabaseProvider.getDatabase(
            applicationContext
        )


        val salavatRepository = SalavatRepository(
            database.salavatRoundDao(),
            database.salavatPersonDao()
        )


        val salavatFactory = SalavatViewModelFactory(
            salavatRepository
        )


        setContent {

            KhatmSalavatTheme {


                AppNavigation(
                    salavatFactory = salavatFactory
                )


            }

        }

    }
}
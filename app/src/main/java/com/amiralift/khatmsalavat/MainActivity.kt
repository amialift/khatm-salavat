package com.amiralift.khatmsalavat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.amiralift.khatmsalavat.navigation.AppNavigation
import com.amiralift.khatmsalavat.ui.theme.KhatmSalavatTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            KhatmSalavatTheme {

                AppNavigation()

            }
        }
    }
}
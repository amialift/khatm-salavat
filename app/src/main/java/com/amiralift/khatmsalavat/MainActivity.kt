package com.amiralift.khatmsalavat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.amiralift.khatmsalavat.ui.screens.home.HomeScreen
import com.amiralift.khatmsalavat.ui.theme.KhatmSalavatTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            KhatmSalavatTheme {
                HomeScreen()
            }
        }
    }
}
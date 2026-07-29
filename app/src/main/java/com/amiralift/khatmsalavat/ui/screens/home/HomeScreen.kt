package com.amiralift.khatmsalavat.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amiralift.khatmsalavat.ui.components.MenuCard

@Composable
fun HomeScreen() {

    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "ختم صلوات",
                style = MaterialTheme.typography.headlineMedium
            )

            Column(
                modifier = Modifier.padding(top = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                MenuCard(
                    title = "📖 ختم قرآن",
                    onClick = {
                        // بعداً صفحه ختم قرآن باز می‌شود.
                    }
                )

                MenuCard(
                    title = "🌹 قرعه معصومین",
                    onClick = {
                        // بعداً صفحه قرعه معصومین باز می‌شود.
                    }
                )
            }
        }
    }
}
package com.amiralift.khatmsalavat.ui.screens.masoom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.amiralift.khatmsalavat.ui.components.AppTopBar

@Composable
fun MasoomScreen(
    navController: NavController
) {

    Scaffold(

        topBar = {

            AppTopBar(
                title = "قرعه معصومین",
                showBackButton = true,
                onBackClick = {
                    navController.popBackStack()
                }
            )

        }

    ) { innerPadding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center

        ) {

            Text(
                text = "🌹 قرعه معصومین"
            )

        }

    }

}
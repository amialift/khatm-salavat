package com.amiralift.khatmsalavat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amiralift.khatmsalavat.ui.screens.home.HomeScreen
import com.amiralift.khatmsalavat.ui.screens.quran.QuranScreen
import com.amiralift.khatmsalavat.ui.screens.masoom.MasoomScreen
import com.amiralift.khatmsalavat.ui.screens.manage.QuranPartScreen
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable(
            route = "home"
        ) {

            HomeScreen(
                navController = navController
            )

        }


        composable(
            route = "quran"
        ) {

            QuranScreen(
                navController = navController
            )

        }

        composable(
            route = "masoom"
        ) {

            MasoomScreen(
                navController = navController
            )

        }

        composable(
            route = "quran_people"
        ) {

            QuranPartScreen(
                navController = navController
            )

        }

    }

}
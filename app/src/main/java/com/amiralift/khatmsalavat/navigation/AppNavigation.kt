package com.amiralift.khatmsalavat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amiralift.khatmsalavat.ui.screens.home.HomeScreen
import com.amiralift.khatmsalavat.ui.screens.quran.QuranScreen
import com.amiralift.khatmsalavat.ui.screens.masoom.MasoomScreen
import com.amiralift.khatmsalavat.ui.screens.manage.QuranPartScreen
import com.amiralift.khatmsalavat.ui.screens.manage.SalavatRoundScreen
import com.amiralift.khatmsalavat.viewmodel.SalavatViewModelFactory
import com.amiralift.khatmsalavat.ui.screens.manage.SalavatPeopleScreen

@Composable
fun AppNavigation(
    salavatFactory: SalavatViewModelFactory
) {

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


        composable(
            route = "salavat_rounds"
        ) {

            SalavatRoundScreen(

                factory = salavatFactory,

                onRoundClick = { roundId ->

                    navController.navigate(
                        "salavat_people/$roundId"
                    )

                },

                onBackClick = {

                    navController.popBackStack()

                }

            )

        }


        composable(
            route = "salavat_people/{roundId}"
        ) { backStackEntry ->


            val roundId =
                backStackEntry.arguments
                    ?.getString("roundId")
                    ?.toInt()
                    ?: 0



            SalavatPeopleScreen(

                roundId = roundId,

                factory = salavatFactory,

                onBackClick = {

                    navController.popBackStack()

                },

                onDeleteRound = {

                    navController.popBackStack()

                }

            )


        }

    }

}
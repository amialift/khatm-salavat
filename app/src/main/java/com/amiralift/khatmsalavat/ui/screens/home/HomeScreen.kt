package com.amiralift.khatmsalavat.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amiralift.khatmsalavat.ui.components.AppDrawer
import com.amiralift.khatmsalavat.ui.components.AppTopBar
import com.amiralift.khatmsalavat.ui.components.MenuCard
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController
) {

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    fun navigateFromDrawer(route: String) {

        scope.launch {
            drawerState.close()
        }

        navController.navigate(route) {

            launchSingleTop = true

            restoreState = true

            popUpTo("home") {
                saveState = true
            }

        }
    }

    ModalNavigationDrawer(

        drawerState = drawerState,

        drawerContent = {

            AppDrawer(

                onQuranPeopleClick = {

                    navigateFromDrawer("quran_people")

                },

                onSalavatRoundsClick = {

                    navigateFromDrawer("salavat_rounds")

                }

            )

        }

    ) {

        Scaffold(

            topBar = {

                AppTopBar(

                    title = "ختم صلوات",

                    onMenuClick = {

                        scope.launch {

                            if (drawerState.currentValue == DrawerValue.Closed) {

                                drawerState.open()

                            }

                        }

                    }

                )

            }

        ) { innerPadding ->

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp),

                horizontalAlignment = Alignment.CenterHorizontally,

                verticalArrangement = Arrangement.Center

            ) {

                Column(

                    verticalArrangement = Arrangement.spacedBy(16.dp)

                ) {

                    MenuCard(

                        title = "📖 ختم قرآن",

                        onClick = {

                            navController.navigate("quran")

                        }

                    )

                    MenuCard(

                        title = "🌹 قرعه معصومین",

                        onClick = {

                            navController.navigate("masoom")

                        }

                    )

                }

            }

        }

    }

}
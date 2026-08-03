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
import androidx.navigation.NavController
import com.amiralift.khatmsalavat.ui.components.AppDrawer
import com.amiralift.khatmsalavat.ui.components.AppTopBar
import com.amiralift.khatmsalavat.ui.components.MenuCard
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.dp


@Composable
fun HomeScreen(
    navController: NavController
) {


    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )


    val scope = rememberCoroutineScope()



    ModalNavigationDrawer(

        drawerState = drawerState,


        drawerContent = {


            AppDrawer(

                onQuranPeopleClick = {


                    scope.launch {

                        try {

                            drawerState.close()

                        } catch (_: CancellationException) {


                        }


                        navController.navigate(
                            "quran_people"
                        )

                    }


                },


                onSalavatRoundsClick = {


                    scope.launch {

                        try {

                            drawerState.close()

                        } catch (_: CancellationException) {


                        }


                        navController.navigate(
                            "salavat_rounds"
                        )

                    }


                }


            )


        }



    ) {



        Scaffold(


            topBar = {


                AppTopBar(


                    title = "ختم صلوات",


                    onMenuClick = {


                        if (!drawerState.isOpen &&
                            !drawerState.isAnimationRunning
                        ) {


                            scope.launch {


                                try {


                                    drawerState.open()


                                } catch (_: CancellationException) {


                                }


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


                            navController.navigate(
                                "quran"
                            )


                        }


                    )



                    MenuCard(


                        title = "🌹 قرعه معصومین",


                        onClick = {


                            navController.navigate(
                                "masoom"
                            )


                        }


                    )


                }


            }



        }


    }


}
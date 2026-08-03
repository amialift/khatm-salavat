package com.amiralift.khatmsalavat.ui.screens.manage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.amiralift.khatmsalavat.data.local.DatabaseProvider
import com.amiralift.khatmsalavat.data.repository.QuranPartRepository
import com.amiralift.khatmsalavat.ui.components.AppTopBar
import com.amiralift.khatmsalavat.viewmodel.QuranPartViewModel
import com.amiralift.khatmsalavat.viewmodel.QuranPartViewModelFactory


@Composable
fun QuranPartScreen(
    navController: NavController
) {

    val context = LocalContext.current


    val database = DatabaseProvider.getDatabase(
        context
    )


    val repository = QuranPartRepository(
        database.quranPartDao()
    )


    val viewModel: QuranPartViewModel = viewModel(
        factory = QuranPartViewModelFactory(
            repository
        )
    )


    val parts by viewModel.parts.collectAsState()


    LaunchedEffect(Unit) {

        viewModel.initializeParts()

    }


    Scaffold(

        topBar = {

            AppTopBar(
                title = "مدیریت ختم قرآن"
                showBackButton = true,
                onBackClick = {
                    navController.popBackStack()
                }
            )

        }

    ) { padding ->


        LazyColumn(

            modifier = Modifier

                .fillMaxSize()

                .padding(padding)

                .padding(16.dp)

                .imePadding(),


            verticalArrangement = Arrangement.spacedBy(12.dp),


            contentPadding = PaddingValues(
                bottom = 32.dp
            )

        ) {


            items(parts) { part ->


                var name by remember(
                    part.name
                ) {

                    mutableStateOf(
                        part.name
                    )

                }


                OutlinedTextField(

                    value = name,


                    onValueChange = {

                        name = it


                        viewModel.updatePart(
                            part.partNumber,
                            it
                        )

                    },


                    label = {

                        Text(
                            "جزء ${part.partNumber}"
                        )

                    },


                    modifier = Modifier.fillMaxWidth()

                )


            }


        }


    }

}
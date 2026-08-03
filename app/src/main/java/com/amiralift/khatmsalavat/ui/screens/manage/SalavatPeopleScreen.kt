package com.amiralift.khatmsalavat.ui.screens.manage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amiralift.khatmsalavat.data.local.SalavatPersonEntity
import com.amiralift.khatmsalavat.data.local.SalavatRoundEntity
import com.amiralift.khatmsalavat.ui.components.AppTopBar
import com.amiralift.khatmsalavat.viewmodel.SalavatViewModel
import com.amiralift.khatmsalavat.viewmodel.SalavatViewModelFactory
import androidx.compose.foundation.layout.imePadding


@Composable
fun SalavatPeopleScreen(

    roundId: Int,

    factory: SalavatViewModelFactory,

    onBackClick: () -> Unit,

    onDeleteRound: () -> Unit

) {


    val viewModel: SalavatViewModel = viewModel(
        factory = factory
    )


    val people = remember {

        mutableStateListOf<SalavatPersonEntity>()

    }


    var displayNumber by remember {

        mutableStateOf(0)

    }


    var showDeleteDialog by remember {

        mutableStateOf(false)

    }



    LaunchedEffect(roundId) {


        viewModel.getPeople(roundId) { result ->

            people.clear()

            people.addAll(result)

        }


        viewModel.getRoundDisplayNumber(roundId) { number ->

            displayNumber = number

        }

    }



    Scaffold(

        topBar = {


            AppTopBar(

                title = "دور $displayNumber",

                showBackButton = true,

                onBackClick = onBackClick

            )

        }

    ) { padding ->



        LazyColumn(

            modifier = Modifier

                .fillMaxSize()

                .padding(padding)

                .padding(16.dp)

                .imePadding(),


            contentPadding = PaddingValues(
                bottom = 40.dp
            ),


            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {



            items(people) { person ->



                var name by remember(person.id) {

                    mutableStateOf(
                        person.name
                    )

                }



                OutlinedTextField(

                    value = name,


                    onValueChange = {


                        name = it


                        viewModel.updatePersonName(

                            roundId,

                            person.slotNumber,

                            it

                        )


                    },


                    label = {

                        Text(
                            "نفر ${person.slotNumber}"
                        )

                    },


                    modifier = Modifier.fillMaxWidth()

                )


            }



            item {


                Button(

                    onClick = {

                        showDeleteDialog = true

                    },


                    modifier = Modifier.fillMaxWidth()

                ) {


                    Text(
                        "حذف دور"
                    )


                }


            }


        }


    }




    if (showDeleteDialog) {


        AlertDialog(


            onDismissRequest = {

                showDeleteDialog = false

            },


            title = {

                Text(
                    "حذف دور"
                )

            },


            text = {

                Text(
                    "آیا از حذف این دور مطمئن هستید؟"
                )

            },


            confirmButton = {


                TextButton(

                    onClick = {


                        viewModel.deleteRound(

                            SalavatRoundEntity(
                                id = roundId
                            )

                        )


                        showDeleteDialog = false


                        onDeleteRound()


                    }

                ) {


                    Text(
                        "حذف"
                    )

                }


            },


            dismissButton = {


                TextButton(

                    onClick = {

                        showDeleteDialog = false

                    }

                ) {


                    Text(
                        "لغو"
                    )

                }

            }

        )

    }


}
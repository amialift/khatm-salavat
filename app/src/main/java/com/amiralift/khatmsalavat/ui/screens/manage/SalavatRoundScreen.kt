package com.amiralift.khatmsalavat.ui.screens.manage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amiralift.khatmsalavat.ui.components.AppTopBar
import com.amiralift.khatmsalavat.viewmodel.SalavatViewModel
import com.amiralift.khatmsalavat.viewmodel.SalavatViewModelFactory


@Composable
fun SalavatRoundScreen(

    factory: SalavatViewModelFactory,

    onRoundClick: (Int) -> Unit,

    onBackClick: () -> Unit

) {


    val viewModel: SalavatViewModel = viewModel(
        factory = factory
    )


    val rounds by viewModel.rounds
        .collectAsStateWithLifecycle()



    Scaffold(

        topBar = {

            AppTopBar(

                title = "مدیریت ختم صلوات",

                showBackButton = true,

                onBackClick = onBackClick

            )

        },


        floatingActionButton = {

            FloatingActionButton(

                onClick = {

                    viewModel.addRound()

                }

            ) {


                Icon(

                    imageVector = Icons.Default.Add,

                    contentDescription = "افزودن دور"

                )

            }

        }

    ) { padding ->



        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),


            contentPadding = PaddingValues(),


            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {



            itemsIndexed(rounds) { index, round ->



                Button(

                    onClick = {

                        onRoundClick(
                            round.id
                        )

                    },


                    modifier = Modifier.fillMaxWidth()

                ) {


                    Text(

                        text = "دور ${index + 1}"

                    )


                }


            }


        }


    }


}
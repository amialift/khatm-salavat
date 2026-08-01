package com.amiralift.khatmsalavat.ui.screens.masoom

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.amiralift.khatmsalavat.data.local.DatabaseProvider
import com.amiralift.khatmsalavat.data.repository.SalavatRepository
import com.amiralift.khatmsalavat.domain.salavat.SalavatShareFormatter
import com.amiralift.khatmsalavat.ui.components.AppTopBar
import com.amiralift.khatmsalavat.viewmodel.MasoomViewModel
import com.amiralift.khatmsalavat.viewmodel.MasoomViewModelFactory

@Composable
fun MasoomScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val clipboard = remember {

        context.getSystemService(
            ClipboardManager::class.java
        )

    }

    val database =
        DatabaseProvider.getDatabase(
            context
        )

    val repository =
        SalavatRepository(

            database.salavatRoundDao(),

            database.salavatPersonDao()

        )

    val viewModel: MasoomViewModel = viewModel(

        factory = MasoomViewModelFactory(
            repository
        )

    )

    val results by viewModel.results
        .collectAsStateWithLifecycle()

    val shareText = remember(results) {

        SalavatShareFormatter.format(
            results
        )

    }

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

    ) { padding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {

            Button(

                modifier = Modifier.fillMaxWidth(),

                onClick = {

                    viewModel.drawAllRounds()

                }

            ) {

                Text(
                    "🌹 انجام قرعه کشی"
                )

            }

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.spacedBy(8.dp)

            ) {

                Button(

                    modifier =
                        Modifier.weight(1f),

                    enabled =
                        results.isNotEmpty(),

                    onClick = {

                        clipboard.setPrimaryClip(

                            ClipData.newPlainText(

                                "MasoomLottery",

                                shareText

                            )

                        )

                        Toast.makeText(

                            context,

                            "نتیجه کپی شد.",

                            Toast.LENGTH_SHORT

                        ).show()

                    }

                ) {

                    Text("📋 کپی")

                }

                Button(

                    modifier =
                        Modifier.weight(1f),

                    enabled =
                        results.isNotEmpty(),

                    onClick = {

                        val intent = Intent(
                            Intent.ACTION_SEND
                        ).apply {

                            type = "text/plain"

                            putExtra(
                                Intent.EXTRA_TEXT,
                                shareText
                            )

                        }

                        context.startActivity(

                            Intent.createChooser(

                                intent,

                                "اشتراک گذاری نتیجه قرعه"

                            )

                        )

                    }

                ) {

                    Text("📤 اشتراک")

                }

            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            LazyColumn(

                verticalArrangement =
                    Arrangement.spacedBy(8.dp)

            ) {

                items(results) { round ->

                    Card(

                        modifier = Modifier
                            .fillMaxWidth()

                    ) {

                        Column(

                            modifier = Modifier
                                .padding(16.dp),

                            verticalArrangement =
                                Arrangement.spacedBy(8.dp)

                        ) {

                            Text(

                                text = "دور ${round.roundNumber}"

                            )

                            round.results.forEachIndexed { index, result ->

                                Text(

                                    text =
                                        "${index + 1}: ${result.personName}   ${result.masoomName}"

                                )

                            }

                        }

                    }

                }

            }

        }

    }

}
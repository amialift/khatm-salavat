package com.amiralift.khatmsalavat.ui.screens.quran

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.amiralift.khatmsalavat.data.local.DatabaseProvider
import com.amiralift.khatmsalavat.data.preferences.UserPreferences
import com.amiralift.khatmsalavat.data.repository.QuranPartRepository
import com.amiralift.khatmsalavat.domain.quran.QuranShareFormatter
import com.amiralift.khatmsalavat.ui.components.AppTopBar
import com.amiralift.khatmsalavat.viewmodel.QuranViewModel
import com.amiralift.khatmsalavat.viewmodel.QuranViewModelFactory

@Composable
fun QuranScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val clipboard = remember {
        context.getSystemService(
            ClipboardManager::class.java
        )
    }

    val database =
        DatabaseProvider.getDatabase(context)

    val repository =
        QuranPartRepository(
            database.quranPartDao()
        )

    val preferences =
        UserPreferences(context)

    val viewModel: QuranViewModel = viewModel(
        factory = QuranViewModelFactory(
            repository,
            preferences
        )
    )

    val result by viewModel.result.collectAsState()

    var roundText by remember {
        mutableStateOf("1")
    }

    val shareText = remember(
        result,
        roundText
    ) {
        QuranShareFormatter.format(
            round = roundText.toIntOrNull() ?: 1,
            parts = result
        )
    }

    LaunchedEffect(Unit) {

        preferences.lastRound.collect { lastRound ->

            roundText = lastRound.toString()

        }

    }

    Scaffold(

        topBar = {

            AppTopBar(
                title = "ختم قرآن",
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
                .padding(16.dp)

        ) {

            OutlinedTextField(

                value = roundText,

                onValueChange = {
                    roundText = it
                },

                label = {
                    Text("شماره دور")
                },

                modifier = Modifier.fillMaxWidth()

            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Button(

                modifier = Modifier.fillMaxWidth(),

                onClick = {

                    val round =
                        roundText.toIntOrNull()
                            ?.takeIf { it > 0 }
                            ?: 1

                    viewModel.calculate(
                        round
                    )

                }

            ) {

                Text(
                    "نمایش ختم"
                )

            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {

                Button(

                    modifier = Modifier.weight(1f),

                    enabled = result.isNotEmpty(),

                    onClick = {

                        clipboard.setPrimaryClip(

                            ClipData.newPlainText(
                                "KhatmQuran",
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

                    modifier = Modifier.weight(1f),

                    enabled = result.isNotEmpty(),

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
                                "اشتراک گذاری نتیجه ختم"
                            )

                        )

                    }

                ) {

                    Text("📤 اشتراک")

                }

            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            LazyColumn {

                items(result) { part ->

                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 4.dp
                            )

                    ) {

                        Text(

                            text =
                                "جزء ${part.partNumber} : ${part.name}",

                            modifier = Modifier
                                .padding(16.dp)

                        )

                    }

                }

            }

        }

    }

}
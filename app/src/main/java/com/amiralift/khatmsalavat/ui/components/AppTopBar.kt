package com.amiralift.khatmsalavat.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    showBackButton: Boolean = false,
    onMenuClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {

    CenterAlignedTopAppBar(

        title = {
            Text(
                text = title
            )
        },

        navigationIcon = {

            IconButton(

                onClick = {
                    if (showBackButton) {
                        onBackClick()
                    } else {
                        onMenuClick()
                    }
                }

            ) {

                Icon(

                    imageVector = if (showBackButton) {
                        Icons.Default.ArrowBack
                    } else {
                        Icons.Default.Menu
                    },

                    contentDescription = null

                )

            }

        }

    )

}
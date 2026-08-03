package com.amiralift.khatmsalavat.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AppDrawer(
    onQuranPeopleClick: () -> Unit,
    onSalavatRoundsClick: () -> Unit
) {

    ModalDrawerSheet(
        modifier = Modifier.width(280.dp)
    ) {


        NavigationDrawerItem(

            label = {
                Text(
                    text = "مدیریت ختم قرآن"
                )
            },

            selected = false,

            onClick = {

                onQuranPeopleClick()

            }

        )


        NavigationDrawerItem(

            label = {
                Text(
                    text = "مدیریت ختم صلوات"
                )
            },

            selected = false,

            onClick = {

                onSalavatRoundsClick()

            }

        )


        NavigationDrawerItem(

            label = {
                Text(
                    text = "درباره برنامه"
                )
            },

            selected = false,

            onClick = {

            }

        )

    }

}
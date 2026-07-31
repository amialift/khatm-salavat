package com.amiralift.khatmsalavat.ui.components

import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AppDrawer(
    onQuranPeopleClick: () -> Unit
) {

    ModalDrawerSheet {

        Text(
            text = "ختم صلوات"
        )


        NavigationDrawerItem(

            label = {
                Text(
                    text = "مدیریت افراد ختم قرآن"
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
                    text = "مدیریت افراد قرعه معصومین"
                )
            },

            selected = false,

            onClick = {

            }

        )


        NavigationDrawerItem(

            label = {
                Text(
                    text = "مدیریت اسامی معصومین"
                )
            },

            selected = false,

            onClick = {

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
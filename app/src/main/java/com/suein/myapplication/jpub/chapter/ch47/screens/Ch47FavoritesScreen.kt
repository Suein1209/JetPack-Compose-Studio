package com.suein.myapplication.jpub.chapter.ch47.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

interface Ch47FavoritesScreen {

    @Composable
    fun FavoritesScreen() {
        Box(modifier = Modifier.fillMaxSize()) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "favorites",
                tint = Color.Blue,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.Center)
            )
        }
    }

}
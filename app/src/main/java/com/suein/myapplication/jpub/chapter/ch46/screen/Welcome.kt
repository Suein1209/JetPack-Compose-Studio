package com.suein.myapplication.jpub.chapter.ch46.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.suein.myapplication.jpub.chapter.ch46.NavRoute

@Composable
fun Welcome(navController: NavController, userName: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Welcome $userName", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.size(30.dp))
            Button(onClick = {
                navController.navigate(NavRoute.Profile.rout) {
                    popUpTo(NavRoute.Home.rout)
                }
            }) {
                Text(text = "Set up your Profile")
            }
        }
    }
}

package com.suein.myapplication.jpub.chapter.ch46

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.suein.myapplication.jpub.chapter.ch46.screen.MainScreen

sealed class NavRoute(val rout: String) {
    object Home : NavRoute("home")
    object Welcome : NavRoute("welcome")
    object Profile : NavRoute("profile")
    object Test : NavRoute("Test")
}

class Chapter46NavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }
}
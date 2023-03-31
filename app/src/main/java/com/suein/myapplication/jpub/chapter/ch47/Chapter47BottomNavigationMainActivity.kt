package com.suein.myapplication.jpub.chapter.ch47

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.suein.myapplication.jpub.chapter.ch47.screens.Ch47MainScreen


sealed class Ch47NavRoutes(val route: String) {
    object Home : Ch47NavRoutes("Home")
    object Contracts : Ch47NavRoutes("Contracts")
    object Favorites : Ch47NavRoutes("Favorites")
}

data class BarItem(
    val title: String,
    val image: ImageVector,
    val route: String
)

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = Ch47NavRoutes.Home.route,
            image = Icons.Filled.Home,
            route = Ch47NavRoutes.Home.route
        ),
        BarItem(
            title = Ch47NavRoutes.Contracts.route,
            image = Icons.Filled.Face,
            route = Ch47NavRoutes.Contracts.route
        ),
        BarItem(
            title = Ch47NavRoutes.Favorites.route,
            image = Icons.Filled.Favorite,
            route = Ch47NavRoutes.Favorites.route
        )
    )
}

class Chapter47BottomNavigationMainActivity : ComponentActivity(), Ch47MainScreen {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    @Preview(showBackground = true)
    fun DefaultPreview() {
        MainScreen()
    }

}
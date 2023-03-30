package com.suein.myapplication.jpub.chapter.ch46.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.suein.myapplication.jpub.chapter.ch46.NavRoute

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoute.Home.rout) {
        composable(NavRoute.Home.rout) {
            Home(navController = navController)
        }

        composable(NavRoute.Welcome.rout + "/{userName}") { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName")
            Welcome(navController = navController, userName)
        }

        composable(NavRoute.Profile.rout) {
            Profile(navController = navController)
        }

        composable(NavRoute.Test.rout) {
            Test(navController = navController)
        }
    }
}
package com.suein.myapplication.jpub.chapter.ch47.screens

import android.util.Log
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.suein.myapplication.jpub.chapter.ch47.Ch47NavRoutes
import com.suein.myapplication.jpub.chapter.ch47.NavBarItems

interface Ch47MainScreen : Ch47FavoritesScreen, Ch47HomeScreen, Ch47ContactsScreen {

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        Scaffold(
            content = { NavigationHost(navController = navController) },
            bottomBar = { BottomNavigationBar(navController = navController) }
        )
    }

    @Composable
    fun NavigationHost(navController: NavHostController) {
        Log.e("suein","NavigationHost : $navController")
        NavHost(navController = navController, startDestination = Ch47NavRoutes.Home.route) {
            Log.e("suein","NavigationHost : ")
            composable(Ch47NavRoutes.Home.route) {
                Log.e("suein","NavigationHost : HomeScreen")
                HomeScreen()
            }

            composable(Ch47NavRoutes.Contracts.route) {
                Log.e("suein","NavigationHost : ContactsScreen")
                ContactsScreen()
            }

            composable(Ch47NavRoutes.Favorites.route) {
                Log.e("suein","NavigationHost : FavoritesScreen")
                FavoritesScreen()
            }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavHostController) {
        BottomNavigation {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route

            NavBarItems.BarItems.forEach { navItem ->

                //하나의 하단 아이템, 그냥 BottomNavigation 여기서 for문으로 돌리기만 하면된다. 왜나면 RowScope 이기 때문에
                BottomNavigationItem(
                    selected = currentRoute == navItem.route,
                    onClick = {
                        navController.navigate(navItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(imageVector = navItem.image, contentDescription = navItem.title)
                    },
                    label = {
                        Text(text = navItem.title)
                    })
            }
        }
    }
}
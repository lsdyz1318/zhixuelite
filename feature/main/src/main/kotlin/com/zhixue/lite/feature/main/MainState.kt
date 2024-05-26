package com.zhixue.lite.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.zhixue.lite.feature.home.navigation.navigateToHome
import com.zhixue.lite.feature.main.navigation.MainDestination
import com.zhixue.lite.feature.profile.navigation.navigateToProfile

@Composable
internal fun rememberMainState(
    navController: NavHostController = rememberNavController()
): MainState {
    return remember(navController) {
        MainState(navController = navController)
    }
}

@Stable
internal class MainState(val navController: NavHostController) {

    val destinations: List<MainDestination> = MainDestination.entries

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    fun navigateToDestination(destination: MainDestination) {
        val navOptions = navOptions {
            popUpTo(navController.graph.id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (destination) {
            MainDestination.HOME -> navController.navigateToHome(navOptions)
            MainDestination.PROFILE -> navController.navigateToProfile(navOptions)
        }
    }
}
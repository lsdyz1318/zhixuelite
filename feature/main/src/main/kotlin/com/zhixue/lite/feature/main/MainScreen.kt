package com.zhixue.lite.feature.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zhixue.lite.core.designsystem.component.Divider
import com.zhixue.lite.core.designsystem.component.Icon
import com.zhixue.lite.core.designsystem.component.IconButton
import com.zhixue.lite.core.designsystem.theme.Theme
import com.zhixue.lite.feature.home.navigation.HomeRoute
import com.zhixue.lite.feature.home.navigation.homeRoute
import com.zhixue.lite.feature.main.navigation.MainDestination
import com.zhixue.lite.feature.profile.navigation.profileRoute

@Composable
internal fun MainRoute(
    onReportInfoClick: (String) -> Unit
) {
    val navController = rememberNavController()

    MainScreen(
        navController = navController,
        destinations = MainDestination.entries,
        currentDestination = navController.currentBackStackEntryAsState().value?.destination,
        onDestinationClick = { destination ->
            navController.navigate(destination.route) {
                popUpTo(navController.graph.id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        onReportInfoClick = onReportInfoClick
    )
}

@Composable
internal fun MainScreen(
    navController: NavHostController,
    destinations: List<MainDestination>,
    currentDestination: NavDestination?,
    onDestinationClick: (MainDestination) -> Unit,
    onReportInfoClick: (String) -> Unit
) {
    Column {
        NavHost(
            navController = navController,
            startDestination = HomeRoute,
            modifier = Modifier.weight(1f),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            homeRoute(
                onReportInfoClick = onReportInfoClick
            )
            profileRoute()
        }
        Divider()
        MainBottomBar(
            destinations = destinations,
            currentDestination = currentDestination,
            onDestinationClick = onDestinationClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 4.dp)
        )
    }
}

@Composable
internal fun MainBottomBar(
    destinations: List<MainDestination>,
    currentDestination: NavDestination?,
    onDestinationClick: (MainDestination) -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        destinations.forEach { destination ->
            val isSelected =
                currentDestination?.route?.contains(destination.name, ignoreCase = true) ?: false

            IconButton(
                onClick = { onDestinationClick(destination) },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(destination.iconResId),
                    tint = if (isSelected) Theme.colorScheme.primary else Theme.colorScheme.onBackgroundVariant
                )
            }
        }
    }
}
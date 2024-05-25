package com.zhixue.lite.feature.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zhixue.lite.core.designsystem.component.Divider
import com.zhixue.lite.core.designsystem.component.Icon
import com.zhixue.lite.core.designsystem.component.IconButton
import com.zhixue.lite.core.designsystem.theme.Theme
import com.zhixue.lite.feature.home.navigation.HomeRoute
import com.zhixue.lite.feature.main.navigation.MainDestination
import com.zhixue.lite.feature.main.navigation.MainNavHost

@Composable
internal fun MainRoute() {

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
        }
    )
}

@Composable
internal fun MainScreen(
    navController: NavHostController,
    destinations: List<MainDestination>,
    currentDestination: NavDestination?,
    onDestinationClick: (MainDestination) -> Unit
) {
    Column {
        MainNavHost(
            navController = navController,
            startDestination = HomeRoute,
            modifier = Modifier.weight(1f)
        )
        Divider()
        MainBottomBar(
            destinations = destinations,
            currentDestination = currentDestination,
            onDestinationClick = onDestinationClick,
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
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
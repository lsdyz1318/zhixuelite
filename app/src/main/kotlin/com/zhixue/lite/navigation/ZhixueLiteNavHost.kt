package com.zhixue.lite.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.zhixue.lite.feature.login.navigation.LoginRoute
import com.zhixue.lite.feature.login.navigation.loginRoute
import com.zhixue.lite.feature.main.navigation.MainRoute
import com.zhixue.lite.feature.main.navigation.mainRoute

@Composable
fun ZhixueLiteNavHost(
    navController: NavHostController,
    startDestination: Any,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        navigation<AuthGraph>(startDestination = LoginRoute) {
            loginRoute(
                onLoginSuccess = {
                    navController.navigate(MainGraph) {
                        popUpTo(AuthGraph) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onRegisterClick = {},
                onForgetPasswordClick = {}
            )
        }
        navigation<MainGraph>(startDestination = MainRoute) {
            mainRoute()
        }
    }
}
package com.zhixue.lite.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.zhixue.lite.feature.login.navigation.loginRoute
import com.zhixue.lite.feature.main.navigation.mainRoute
import com.zhixue.lite.feature.report.detail.navigation.reportDetailRoute
import com.zhixue.lite.ui.ZhixueLiteAppState

@Composable
fun ZhixueLiteNavHost(
    appState: ZhixueLiteAppState,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = appState.navController,
        startDestination = appState.currentStartDestination,
        modifier = modifier
    ) {
        loginRoute(
            onLoginSuccess = appState::navigateToMain,
            onRegisterClick = {},
            onForgetPasswordClick = {}
        )
        mainRoute(onReportInfoClick = appState::navigateToReportDetail)
        reportDetailRoute()
    }
}
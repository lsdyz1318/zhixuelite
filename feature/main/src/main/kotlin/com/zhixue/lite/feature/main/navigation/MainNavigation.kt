package com.zhixue.lite.feature.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zhixue.lite.feature.main.MainRoute
import kotlinx.serialization.Serializable

@Serializable
object Main

fun NavController.navigateToMain(navOptions: NavOptions? = null) {
    navigate(Main, navOptions)
}

fun NavGraphBuilder.mainScreen(
    onReportInfoClick: (String) -> Unit
) {
    composable<Main> {
        MainRoute(onReportInfoClick = onReportInfoClick)
    }
}
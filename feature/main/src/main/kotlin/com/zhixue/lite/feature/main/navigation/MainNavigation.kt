package com.zhixue.lite.feature.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zhixue.lite.feature.main.MainRoute
import kotlinx.serialization.Serializable

@Serializable
object MainRoute

fun NavGraphBuilder.mainRoute(
    onReportInfoClick: (String) -> Unit
) {
    composable<MainRoute> {
        MainRoute(
            onReportInfoClick = onReportInfoClick
        )
    }
}
package com.zhixue.lite.feature.report.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zhixue.lite.feature.report.detail.ReportDetailRoute
import kotlinx.serialization.Serializable

@Serializable
data class ReportDetailRoute(val reportId: String)

fun NavController.navigateToReportDetail(reportId: String, navOptions: NavOptions? = null) {
    navigate(ReportDetailRoute(reportId), navOptions)
}

fun NavGraphBuilder.reportDetailScreen(
    onBackClick: () -> Unit,
    onOverviewInfoClick: (String) -> Unit
) {
    composable<ReportDetailRoute> {
        ReportDetailRoute(
            onBackClick = onBackClick,
            onOverviewInfoClick = onOverviewInfoClick
        )
    }
}
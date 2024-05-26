package com.zhixue.lite.feature.report.detail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zhixue.lite.feature.report.detail.ReportDetailRoute
import kotlinx.serialization.Serializable

@Serializable
data class ReportDetailRoute(val reportId: String)

fun NavGraphBuilder.reportDetailRoute() {
    composable<ReportDetailRoute> {
        ReportDetailRoute()
    }
}
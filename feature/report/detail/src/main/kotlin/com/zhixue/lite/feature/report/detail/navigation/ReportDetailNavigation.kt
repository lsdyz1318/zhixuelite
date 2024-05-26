package com.zhixue.lite.feature.report.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zhixue.lite.feature.report.detail.ReportDetailRoute
import kotlinx.serialization.Serializable

@Serializable
data class ReportDetail(val reportId: String)

fun NavController.navigateToReportDetail(reportId: String, navOptions: NavOptions? = null) {
    navigate(ReportDetail(reportId), navOptions)
}

fun NavGraphBuilder.reportDetailScreen() {
    composable<ReportDetail> {
        ReportDetailRoute()
    }
}
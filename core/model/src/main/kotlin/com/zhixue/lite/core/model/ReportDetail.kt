package com.zhixue.lite.core.model

data class ReportDetail(
    val totalInfo: TotalInfo,
    val overviewInfoList: List<OverviewInfo>,
    val trendInfoList: List<TrendInfo>
) {
    data class TotalInfo(
        val userScore: String,
        val standardScore: String,
        val scoreRate: Float
    )

    data class OverviewInfo(
        val paperId: String,
        val subjectName: String,
        val userScore: String,
        val standardScore: String,
        val scoreRate: Float,
        val trendLevel: String
    )

    data class TrendInfo(
        val subjectName: String,
        val classRank: String,
        val trendDirection: TrendDirection?
    )
}
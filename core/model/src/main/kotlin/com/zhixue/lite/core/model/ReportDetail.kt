package com.zhixue.lite.core.model

data class ReportDetail(
    val totalInfo: TotalInfo,
    val overviewInfoList: List<OverviewInfo>
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
        val level: String,
        val classRank: String,
        val direction: TrendDirection?
    )
}
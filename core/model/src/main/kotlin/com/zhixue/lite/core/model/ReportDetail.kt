package com.zhixue.lite.core.model

data class ReportDetail(
    val totalInfo: TotalInfo,
    val paperInfoList: List<PaperInfo>
) {
    data class TotalInfo(
        val userScore: String,
        val standardScore: String,
        val scoreRate: Float
    )

    data class PaperInfo(
        val paperId: String,
        val subjectName: String,
        val userScore: String,
        val standardScore: String,
        val scoreRate: Float,
        val classRank: String,
        val trendLevel: String,
        val trendDirection: TrendDirection?
    )
}
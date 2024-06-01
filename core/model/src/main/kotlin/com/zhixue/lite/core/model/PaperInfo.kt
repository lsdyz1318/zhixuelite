package com.zhixue.lite.core.model

data class PaperInfo(
    val paperId: String,
    val subjectName: String,
    val userScore: Double?,
    val standardScore: Double,
    val scoreRate: Float,
    val classRank: Int?,
    val classPercentile: Double?,
    val trendLevel: String?,
    val trendDirection: TrendDirection?,
    val studentNumber: Int?
)
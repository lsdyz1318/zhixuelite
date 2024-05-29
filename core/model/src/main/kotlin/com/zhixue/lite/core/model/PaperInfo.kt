package com.zhixue.lite.core.model

data class PaperInfo(
    val paperId: String,
    val subjectCode: String,
    val subjectName: String,
    val userScore: Double,
    val standardScore: Double,
    val classRank: Int?,
    val trendInfoList: List<TrendInfo>
)
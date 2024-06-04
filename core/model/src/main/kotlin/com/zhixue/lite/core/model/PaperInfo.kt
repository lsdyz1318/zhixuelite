package com.zhixue.lite.core.model

data class PaperInfo(
    val id: String,
    val subjectName: String,
    val userScore: Double?,
    val standardScore: Double,
    val scoreRate: Float,
    val classRank: Int?,
    val classPercentile: Double?,
    val classTrendInfo: TrendInfo?
)

data class FormatPaperInfo(
    val id: String,
    val subjectName: String,
    val userScore: String,
    val standardScore: String,
    val scoreRate: Float,
    val level: String,
    val classRank: String,
    val direction: TrendDirection?
)
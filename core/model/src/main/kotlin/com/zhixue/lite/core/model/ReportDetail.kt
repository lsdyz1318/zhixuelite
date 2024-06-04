package com.zhixue.lite.core.model

data class ReportDetail(
    val totalInfo: TotalInfo,
    val overview: List<FormatPaperInfo>
) {
    data class TotalInfo(
        val userScore: String,
        val standardScore: String,
        val scoreRate: Float
    )
}
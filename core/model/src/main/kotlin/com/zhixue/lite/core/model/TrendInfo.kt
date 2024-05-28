package com.zhixue.lite.core.model

data class TrendInfo(
    val label: String,
    val trendCode: String,
    val trendLevel: String,
    val studentNumber: Int,
    val trendOffsetList: List<Int>
)
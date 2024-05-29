package com.zhixue.lite.core.model

data class TrendInfo(
    val label: String,
    val level: String,
    val trendCode: String,
    val trendOffsets: List<Int>,
    val studentNumber: Int
)
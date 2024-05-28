package com.zhixue.lite.core.database.model

import androidx.room.Entity

@Entity(primaryKeys = ["paperId", "label"])
data class TrendInfoEntity(
    val paperId: String,
    val label: String,
    val trendCode: String,
    val trendLevel: String,
    val studentNumber: Int,
    val trendOffsetList: List<Int>
)
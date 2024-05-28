package com.zhixue.lite.core.database.model

import androidx.room.Entity

@Entity(primaryKeys = ["subjectId", "label"])
data class TrendInfoEntity(
    val subjectId: String,
    val label: String,
    val trendCode: String,
    val trendLevel: String,
    val studentNumber: Int,
    val trendOffsetList: List<Int>
)
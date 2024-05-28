package com.zhixue.lite.core.database.model

import androidx.room.Entity
import com.zhixue.lite.core.model.TrendInfo

@Entity(primaryKeys = ["paperId", "label"])
data class TrendInfoEntity(
    val paperId: String,
    val label: String,
    val trendCode: String,
    val trendLevel: String,
    val studentNumber: Int,
    val trendOffsetList: List<Int>
)

fun TrendInfoEntity.asExternalModel(): TrendInfo = TrendInfo(
    label = label,
    trendCode = trendCode,
    trendLevel = trendLevel,
    studentNumber = studentNumber,
    trendOffsetList = trendOffsetList
)
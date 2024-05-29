package com.zhixue.lite.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.zhixue.lite.core.model.TrendInfo

@Entity(
    tableName = "trend_info",
    primaryKeys = ["paper_id", "label"]
)
data class TrendInfoEntity(
    @ColumnInfo(name = "paper_id")
    val paperId: String,
    @ColumnInfo(name = "label")
    val label: String,
    @ColumnInfo(name = "level")
    val level: String,
    @ColumnInfo(name = "trend_code")
    val trendCode: String,
    @ColumnInfo(name = "trend_offsets")
    val trendOffsets: List<Int>,
    @ColumnInfo(name = "student_number")
    val studentNumber: Int
)

fun TrendInfoEntity.asExternalModel() : TrendInfo = TrendInfo(
    label = label,
    level = level,
    trendCode = trendCode,
    trendOffsets = trendOffsets,
    studentNumber = studentNumber
)
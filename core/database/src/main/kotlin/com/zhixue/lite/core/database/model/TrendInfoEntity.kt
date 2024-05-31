package com.zhixue.lite.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.zhixue.lite.core.model.TrendDirection

@Entity(
    tableName = "trend_info",
    primaryKeys = ["trend_id", "trend_code"]
)
data class TrendInfoEntity(
    @ColumnInfo(name = "trend_id")
    val paperId: String,
    @ColumnInfo(name = "trend_code")
    val trendCode: String,
    @ColumnInfo(name = "paper_name")
    val paperName: String,
    @ColumnInfo(name = "datetime")
    val datetime: Long,
    @ColumnInfo(name = "trend_level")
    val trendLevel: String,
    @ColumnInfo(name = "trend_offset")
    val trendOffset: Int,
    @ColumnInfo(name = "trend_direction")
    val trendDirection: TrendDirection,
    @ColumnInfo(name = "student_number")
    val studentNumber: Int
)
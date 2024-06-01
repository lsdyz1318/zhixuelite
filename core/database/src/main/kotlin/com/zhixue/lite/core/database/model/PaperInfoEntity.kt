package com.zhixue.lite.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "paper_info",
    primaryKeys = ["paper_id"]
)
data class PaperInfoEntity(
    @ColumnInfo(name = "report_id")
    val reportId: String,
    @ColumnInfo(name = "paper_id")
    val paperId: String,
    @ColumnInfo(name = "subject_code")
    val subjectCode: String,
    @ColumnInfo(name = "subject_name")
    val subjectName: String,
    @ColumnInfo(name = "user_score")
    val userScore: Double,
    @ColumnInfo(name = "standard_score")
    val standardScore: Double,
    @ColumnInfo(name = "score_rate")
    val scoreRate: Float,
    @ColumnInfo(name = "class_rank")
    val classRank: Int?,
    @ColumnInfo(name = "class_percentile")
    val classPercentile: Double?
)
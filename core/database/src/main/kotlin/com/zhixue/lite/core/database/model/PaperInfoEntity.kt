package com.zhixue.lite.core.database.model

import androidx.room.Entity

@Entity(primaryKeys = ["reportId", "paperId"])
data class PaperInfoEntity(
    val reportId: String,
    val paperId: String,
    val subjectName: String,
    val userScore: Double,
    val standardScore: Double
)
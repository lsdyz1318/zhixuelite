package com.zhixue.lite.core.database.model

import androidx.room.Entity

@Entity(primaryKeys = ["reportId", "subjectId"])
data class SubjectInfoEntity(
    val reportId: String,
    val subjectId: String,
    val subjectName: String,
    val userScore: Double,
    val standardScore: Double
)
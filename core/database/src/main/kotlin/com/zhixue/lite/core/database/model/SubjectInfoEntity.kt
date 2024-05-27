package com.zhixue.lite.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zhixue.lite.core.model.SubjectInfo

@Entity
data class SubjectInfoEntity(
    @PrimaryKey
    val reportId: String,
    val subjectId: String,
    val subjectName: String,
    val userScore: Double,
    val standardScore: Double
)

fun SubjectInfoEntity.asExternalModel(): SubjectInfo = SubjectInfo(
    subjectId = subjectId,
    subjectName = subjectName,
    userScore = userScore,
    standardScore = standardScore
)
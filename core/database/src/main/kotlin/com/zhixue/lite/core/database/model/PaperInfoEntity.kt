package com.zhixue.lite.core.database.model

import androidx.room.Entity
import com.zhixue.lite.core.model.PaperInfo

@Entity(primaryKeys = ["reportId", "paperId"])
data class PaperInfoEntity(
    val reportId: String,
    val paperId: String,
    val subjectName: String,
    val userScore: Double,
    val standardScore: Double
)

fun PaperInfoEntity.asExternalModel(): PaperInfo = PaperInfo(
    paperId = paperId,
    subjectName = subjectName,
    userScore = userScore,
    standardScore = standardScore
)
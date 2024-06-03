package com.zhixue.lite.core.data.model

import com.zhixue.lite.core.database.model.PaperInfoEntity
import com.zhixue.lite.core.network.model.NetworkPaperInfo

fun NetworkPaperInfo.asEntity(
    userId: String,
    reportId: String,
    classPercentile: Double?
): PaperInfoEntity =
    PaperInfoEntity(
        userId = userId,
        id = id,
        reportId = reportId,
        subjectCode = subjectCode,
        subjectName = subjectName,
        userScore = userScore,
        standardScore = standardScore,
        scoreRate = userScore?.div(standardScore)?.toFloat() ?: 0f,
        classRank = classRank,
        classPercentile = classPercentile
    )
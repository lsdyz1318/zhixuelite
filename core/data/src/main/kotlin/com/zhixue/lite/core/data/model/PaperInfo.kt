package com.zhixue.lite.core.data.model

import com.zhixue.lite.core.database.model.PaperInfoEntity
import com.zhixue.lite.core.network.model.NetworkPaperInfo

fun NetworkPaperInfo.asEntity(reportId: String): PaperInfoEntity =
    PaperInfoEntity(
        reportId = reportId,
        paperId = paperId,
        subjectName = subjectName,
        userScore = userScore,
        standardScore = standardScore
    )
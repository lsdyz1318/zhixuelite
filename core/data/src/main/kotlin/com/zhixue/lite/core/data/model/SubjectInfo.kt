package com.zhixue.lite.core.data.model

import com.zhixue.lite.core.database.model.SubjectInfoEntity
import com.zhixue.lite.core.network.model.NetworkSubjectInfo

fun NetworkSubjectInfo.asEntity(reportId: String): SubjectInfoEntity =
    SubjectInfoEntity(
        reportId = reportId,
        subjectId = id,
        subjectName = name,
        userScore = userScore,
        standardScore = standardScore
    )
package com.zhixue.lite.core.data.model

import com.zhixue.lite.core.database.model.SubjectInfoEntity
import com.zhixue.lite.core.network.model.NetworkSubjectInfo

fun NetworkSubjectInfo.asEntity(reportId: String): SubjectInfoEntity = SubjectInfoEntity(
    reportId = reportId,
    subjectId = subjectId,
    subjectName = subjectName,
    userScore = userScore,
    standardScore = standardScore
)
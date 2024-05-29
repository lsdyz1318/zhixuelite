package com.zhixue.lite.core.data.model

import com.zhixue.lite.core.database.model.SubjectDiagnosisInfoEntity
import com.zhixue.lite.core.network.model.NetworkSubjectDiagnosisInfo

fun NetworkSubjectDiagnosisInfo.asEntity(reportId: String): SubjectDiagnosisInfoEntity =
    SubjectDiagnosisInfoEntity(
        reportId = reportId,
        subjectCode = subjectCode,
        userLevel = userLevel
    )
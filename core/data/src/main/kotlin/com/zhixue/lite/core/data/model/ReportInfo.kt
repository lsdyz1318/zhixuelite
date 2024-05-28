package com.zhixue.lite.core.data.model

import com.zhixue.lite.core.database.model.ReportInfoEntity
import com.zhixue.lite.core.network.model.NetworkReportInfo

fun NetworkReportInfo.asEntity(reportType: String, formatDateTime: String): ReportInfoEntity =
    ReportInfoEntity(
        reportId = id,
        reportType = reportType,
        reportName = name,
        dateTime = formatDateTime,
        isPublished = isPublished
    )
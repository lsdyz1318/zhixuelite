package com.zhixue.lite.core.data.model

import com.zhixue.lite.core.database.model.ReportInfoEntity
import com.zhixue.lite.core.network.model.NetworkReportInfo

fun NetworkReportInfo.asEntity(reportType: String, formatDateTime: String): ReportInfoEntity =
    ReportInfoEntity(
        reportId = reportId,
        reportType = reportType,
        reportName = reportName,
        dateTime = formatDateTime,
        isPublished = isPublished
    )
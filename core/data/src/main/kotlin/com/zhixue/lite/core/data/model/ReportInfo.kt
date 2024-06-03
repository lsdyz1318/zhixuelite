package com.zhixue.lite.core.data.model

import com.zhixue.lite.core.database.model.ReportInfoEntity
import com.zhixue.lite.core.network.model.NetworkReportInfo

fun NetworkReportInfo.asEntity(reportType: String): ReportInfoEntity =
    ReportInfoEntity(
        id = id,
        type = reportType,
        name = name,
        createDate = createDate,
        isPublished = isPublished
    )
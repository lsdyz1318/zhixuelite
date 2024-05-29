package com.zhixue.lite.core.data.model

import com.zhixue.lite.core.database.model.TrendInfoEntity
import com.zhixue.lite.core.network.model.NetworkTrendInfo

fun NetworkTrendInfo.asEntity(paperId: String): TrendInfoEntity = TrendInfoEntity(
    paperId = paperId,
    label = tag.name,
    level = improveInfo.level,
    trendCode = improveInfo.tag.code,
    trendOffsets = dataList.map { it.improveInfo.offset },
    studentNumber = studentNumber
)
package com.zhixue.lite.core.data.model

import com.zhixue.lite.core.database.model.TrendInfoEntity
import com.zhixue.lite.core.model.TrendDirection
import com.zhixue.lite.core.network.model.NetworkTrendInfo

fun NetworkTrendInfo.mapToTrendInfoEntities(): List<TrendInfoEntity> =
    dataList.map {
        TrendInfoEntity(
            paperId = it.paperId,
            trendCode = tag.code,
            paperName = it.paperName,
            datetime = it.datetime,
            trendLevel = it.level,
            trendOffset = it.improveInfo.offset,
            trendDirection = when (it.improveInfo.tag.code) {
                "fastUp" -> TrendDirection.FAST_UP
                "slowUp" -> TrendDirection.SLOW_UP
                "slowDown" -> TrendDirection.SLOW_DOWN
                "fastDown" -> TrendDirection.FAST_DOWN
                else -> TrendDirection.STEADY
            },
            studentNumber = it.studentNumber
        )
    }
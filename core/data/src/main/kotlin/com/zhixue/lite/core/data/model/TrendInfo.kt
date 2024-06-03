package com.zhixue.lite.core.data.model

import com.zhixue.lite.core.database.model.TrendInfoEntity
import com.zhixue.lite.core.model.TrendDirection
import com.zhixue.lite.core.network.model.NetworkTrendInfo

fun NetworkTrendInfo.mapToTrendInfoEntities(userId: String): List<TrendInfoEntity> =
    dataList.map {
        TrendInfoEntity(
            userId = userId,
            code = tag.code,
            paperId = it.paperId,
            paperName = it.paperName,
            datetime = it.datetime,
            level = it.level,
            offset = it.improveInfo.offset,
            direction = when (it.improveInfo.tag.code) {
                "fastUp", "slowUp" -> TrendDirection.UP
                "slowDown", "fastDown" -> TrendDirection.DOWN
                else -> TrendDirection.FLAT
            },
            studentNumber = it.studentNumber
        )
    }
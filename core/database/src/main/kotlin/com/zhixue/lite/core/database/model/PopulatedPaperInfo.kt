package com.zhixue.lite.core.database.model

import androidx.room.Embedded

data class PopulatedPaperInfo(
    @Embedded
    val paperInfoEntity: PaperInfoEntity,
    @Embedded
    val trendInfoEntity: TrendInfoEntity?
)
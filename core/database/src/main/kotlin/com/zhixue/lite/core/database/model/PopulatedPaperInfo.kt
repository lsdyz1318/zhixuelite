package com.zhixue.lite.core.database.model

import androidx.room.Embedded
import com.zhixue.lite.core.model.PaperInfo

data class PopulatedPaperInfo(
    @Embedded
    val paperInfoEntity: PaperInfoEntity,
    @Embedded
    val trendInfoEntity: TrendInfoEntity?
)

fun PopulatedPaperInfo.asExternalModel(): PaperInfo = PaperInfo(
    paperId = paperInfoEntity.paperId,
    subjectName = paperInfoEntity.subjectName,
    userScore = paperInfoEntity.userScore,
    standardScore = paperInfoEntity.standardScore,
    scoreRate = paperInfoEntity.scoreRate,
    classRank = paperInfoEntity.classRank,
    classPercentile = paperInfoEntity.classPercentile,
    trendLevel = trendInfoEntity?.trendLevel,
    trendDirection = trendInfoEntity?.trendDirection,
    studentNumber = trendInfoEntity?.studentNumber
)
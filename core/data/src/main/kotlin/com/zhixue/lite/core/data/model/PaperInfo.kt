package com.zhixue.lite.core.data.model

import com.zhixue.lite.core.database.model.PaperInfoEntity
import com.zhixue.lite.core.database.model.TrendInfoEntity
import com.zhixue.lite.core.model.PaperInfo
import com.zhixue.lite.core.network.model.NetworkPaperInfo

fun NetworkPaperInfo.asEntity(reportId: String, classPercentile: Double?): PaperInfoEntity =
    PaperInfoEntity(
        reportId = reportId,
        paperId = paperId,
        subjectCode = subjectCode,
        subjectName = subjectName,
        userScore = userScore,
        standardScore = standardScore,
        scoreRate = userScore?.div(standardScore)?.toFloat() ?: 0f,
        classRank = classRank,
        classPercentile = classPercentile
    )

fun Map<PaperInfoEntity, TrendInfoEntity?>.mapToPaperInfoList(): List<PaperInfo> =
    map { (paperInfoEntity, trendInfoEntity) ->
        PaperInfo(
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
    }
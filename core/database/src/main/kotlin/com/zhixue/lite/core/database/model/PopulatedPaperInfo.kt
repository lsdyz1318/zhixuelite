package com.zhixue.lite.core.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.zhixue.lite.core.model.PaperInfo

data class PopulatedPaperInfo(
    @Embedded
    val paperInfoEntity: PaperInfoEntity,
    @Relation(
        parentColumn = "paper_id",
        entityColumn = "paper_id"
    )
    val trendInfoEntities: List<TrendInfoEntity>
)

fun PopulatedPaperInfo.asExternalModel(): PaperInfo = PaperInfo(
    paperId = paperInfoEntity.paperId,
    subjectCode = paperInfoEntity.subjectCode,
    subjectName = paperInfoEntity.subjectName,
    userScore = paperInfoEntity.userScore,
    standardScore = paperInfoEntity.standardScore,
    trendInfoList = trendInfoEntities.map(TrendInfoEntity::asExternalModel)
)
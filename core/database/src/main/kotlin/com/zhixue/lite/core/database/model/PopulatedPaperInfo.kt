package com.zhixue.lite.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class PopulatedPaperInfo(
    @Embedded
    val paperInfoEntity: PaperInfoEntity,
    @Relation(
        parentColumn = "paper_id",
        entityColumn = "paper_id"
    )
    val trendInfoEntities: List<TrendInfoEntity>,
    @Relation(
        parentColumn = "paper_id",
        entityColumn = "paper_id"
    )
    val subjectDiagnosisInfoEntities: List<SubjectDiagnosisInfoEntity>
)
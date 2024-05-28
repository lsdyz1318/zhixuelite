package com.zhixue.lite.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zhixue.lite.core.model.ReportInfo

@Entity
data class ReportInfoEntity(
    @PrimaryKey
    val reportId: String,
    val reportType: String,
    val reportName: String,
    val dateTime: String,
    val isPublished: Boolean
)

fun ReportInfoEntity.asExternalModel(): ReportInfo = ReportInfo(
    id = reportId,
    name = reportName,
    dateTime = dateTime,
    isPublished = isPublished
)
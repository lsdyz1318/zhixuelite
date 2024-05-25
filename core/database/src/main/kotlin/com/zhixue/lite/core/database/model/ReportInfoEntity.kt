package com.zhixue.lite.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zhixue.lite.core.model.ReportInfo

@Entity
data class ReportInfoEntity(
    @PrimaryKey
    val id: String,
    val type: String,
    val name: String,
    val dateTime: String,
    val isPublished: Boolean
)

fun ReportInfoEntity.asExternalModel(): ReportInfo = ReportInfo(
    id = id,
    name = name,
    dateTime = dateTime,
    isPublished = isPublished
)
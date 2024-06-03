package com.zhixue.lite.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.zhixue.lite.core.model.ReportInfo

@Entity(
    tableName = "report_info",
    primaryKeys = ["user_id", "id"]
)
data class ReportInfoEntity(
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "create_date", index = true)
    val createDate: Long,
    @ColumnInfo(name = "published")
    val isPublished: Boolean
)

fun ReportInfoEntity.asExternalModel(): ReportInfo =
    ReportInfo(
        id = id,
        name = name,
        createDate = createDate,
        isPublished = isPublished
    )
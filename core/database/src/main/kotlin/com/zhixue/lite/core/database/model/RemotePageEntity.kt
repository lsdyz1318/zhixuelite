package com.zhixue.lite.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "remote_page",
    primaryKeys = ["label"]
)
data class RemotePageEntity(
    @ColumnInfo(name = "label")
    val label: String,
    @ColumnInfo(name = "next_page")
    val nextPage: Int
)
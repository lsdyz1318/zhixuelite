package com.zhixue.lite.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "remote_page",
    primaryKeys = ["user_id", "label"]
)
data class RemotePageEntity(
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "label")
    val label: String,
    @ColumnInfo(name = "next_page")
    val nextPage: Int
)
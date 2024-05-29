package com.zhixue.lite.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_page")
data class RemotePageEntity(
    @PrimaryKey
    @ColumnInfo(name = "label")
    val label: String,
    @ColumnInfo(name = "next_page")
    val nextPage: Int
)
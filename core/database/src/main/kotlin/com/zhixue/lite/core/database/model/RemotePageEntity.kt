package com.zhixue.lite.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemotePageEntity(
    @PrimaryKey
    val label: String,
    val nextPage: Int
)
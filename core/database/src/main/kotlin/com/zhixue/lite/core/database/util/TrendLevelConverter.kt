package com.zhixue.lite.core.database.util

import androidx.room.TypeConverter
import com.zhixue.lite.core.model.TrendLevel

internal object TrendLevelConverter {
    @TypeConverter
    fun stringToTrendLevel(value: String): TrendLevel {
        return TrendLevel.valueOf(value)
    }

    @TypeConverter
    fun trendLevelToString(value: TrendLevel): String {
        return value.name
    }
}
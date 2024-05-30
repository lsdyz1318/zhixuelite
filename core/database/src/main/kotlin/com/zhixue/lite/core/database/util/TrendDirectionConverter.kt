package com.zhixue.lite.core.database.util

import androidx.room.TypeConverter
import com.zhixue.lite.core.model.TrendDirection

internal object TrendDirectionConverter {
    @TypeConverter
    fun stringToTrendDirection(value: String): TrendDirection {
        return TrendDirection.valueOf(value)
    }

    @TypeConverter
    fun trendDirectionToString(value: TrendDirection): String {
        return value.name
    }
}
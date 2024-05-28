package com.zhixue.lite.core.database.util

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal object IntListConverter {
    @TypeConverter
    fun converterToJsonString(value: List<Int>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun converterToIntList(value: String): List<Int> {
        return Json.decodeFromString(value)
    }
}
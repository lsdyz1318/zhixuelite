package com.zhixue.lite.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.zhixue.lite.core.database.model.TrendInfoEntity

@Dao
interface TrendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendInfoList(entities: List<TrendInfoEntity>)
}
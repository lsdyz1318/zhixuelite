package com.zhixue.lite.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.zhixue.lite.core.database.model.TrendInfoEntity

@Dao
interface TrendInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendInfoEntities(entities: List<TrendInfoEntity>)
}
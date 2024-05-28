package com.zhixue.lite.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zhixue.lite.core.database.model.TrendInfoEntity

@Dao
interface TrendInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendInfoEntities(trendInfoEntities: List<TrendInfoEntity>)

    @Query("SELECT * FROM TrendInfoEntity WHERE paperId = :paperId")
    suspend fun getTrendInfoEntities(paperId: String): List<TrendInfoEntity>
}
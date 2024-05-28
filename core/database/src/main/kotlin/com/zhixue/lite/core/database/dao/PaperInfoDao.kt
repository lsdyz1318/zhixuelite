package com.zhixue.lite.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zhixue.lite.core.database.model.PaperInfoEntity

@Dao
interface PaperInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaperInfoEntities(paperInfoEntities: List<PaperInfoEntity>)

    @Query("SELECT * FROM PaperInfoEntity WHERE reportId = :reportId")
    suspend fun getPaperInfoEntities(reportId: String): List<PaperInfoEntity>
}
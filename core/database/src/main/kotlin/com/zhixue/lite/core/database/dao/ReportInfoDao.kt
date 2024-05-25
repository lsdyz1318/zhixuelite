package com.zhixue.lite.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zhixue.lite.core.database.model.ReportInfoEntity

@Dao
interface ReportInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReportInfoEntities(reportInfoEntities: List<ReportInfoEntity>)

    @Query("SELECT * FROM ReportInfoEntity WHERE type = :type")
    suspend fun getReportInfoEntity(type: String): ReportInfoEntity?

    @Query("SELECT * FROM ReportInfoEntity WHERE type = :type")
    fun getReportInfoPagingSource(type: String): PagingSource<Int, ReportInfoEntity>

    @Query("DELETE FROM ReportInfoEntity WHERE type = :type")
    suspend fun deleteReportInfoEntities(type: String)
}
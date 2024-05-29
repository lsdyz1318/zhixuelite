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
    suspend fun insertReportInfoEntities(entities: List<ReportInfoEntity>)

    @Query("SELECT * FROM report_info WHERE report_type = :reportType")
    suspend fun getReportInfoEntity(reportType: String): ReportInfoEntity?

    @Query("SELECT * FROM report_info WHERE report_type = :reportType")
    fun getReportInfoPagingSource(reportType: String): PagingSource<Int, ReportInfoEntity>

    @Query("DELETE FROM report_info WHERE report_type = :reportType")
    suspend fun deleteReportInfoEntities(reportType: String)
}
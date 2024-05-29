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
    suspend fun insertReportInfoList(entities: List<ReportInfoEntity>)

    @Query("SELECT report_id FROM report_info WHERE report_type = :reportType")
    suspend fun getReportId(reportType: String): String?

    @Query("SELECT * FROM report_info WHERE report_type = :reportType")
    fun reportInfoPagingSource(reportType: String): PagingSource<Int, ReportInfoEntity>

    @Query("DELETE FROM report_info WHERE report_type = :reportType")
    suspend fun deleteAllReportInfo(reportType: String)
}
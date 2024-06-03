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

    @Query(
        """
            SELECT id FROM report_info
            WHERE user_id = :userId AND type = :reportType
        """
    )
    suspend fun getReportInfoLatestId(userId: String, reportType: String): String?

    @Query(
        """
            SELECT * FROM report_info
            WHERE user_id = :userId AND type = :reportType
        """
    )
    fun reportInfoPagingSource(
        userId: String, reportType: String
    ): PagingSource<Int, ReportInfoEntity>

    @Query(
        """
            DELETE FROM report_info
            WHERE user_id = :userId AND type = :reportType
        """
    )
    suspend fun deleteReportInfoList(userId: String, reportType: String)
}
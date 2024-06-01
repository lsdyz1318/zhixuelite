package com.zhixue.lite.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.zhixue.lite.core.database.model.PaperInfoEntity
import com.zhixue.lite.core.database.model.PopulatedPaperInfo

@Dao
interface PaperInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaperInfoList(entities: List<PaperInfoEntity>)

    @Query("SELECT paper_id FROM paper_info WHERE report_id = :reportId")
    suspend fun getPaperInfoIds(reportId: String): List<String>

    @Transaction
    @Query("SELECT * FROM paper_info LEFT JOIN trend_info ON trend_id = paper_id AND trend_code = 'clazz' WHERE report_id = :reportId ORDER BY subject_code ASC")
    suspend fun getPaperInfoList(reportId: String): List<PopulatedPaperInfo>
}
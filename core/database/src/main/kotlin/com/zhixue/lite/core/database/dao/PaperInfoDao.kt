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

    @Query(
        """
            SELECT id FROM paper_info
            WHERE user_id = :userId AND report_id = :reportId
        """
    )
    suspend fun getPaperInfoIds(userId: String, reportId: String): List<String>

    @Transaction
    @Query(
        """
            SELECT * FROM paper_info
            WHERE user_id = :userId AND report_id = :reportId
            ORDER BY subject_code
        """
    )
    suspend fun getPaperInfoList(userId: String, reportId: String): List<PopulatedPaperInfo>
}
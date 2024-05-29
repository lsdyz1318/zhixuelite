package com.zhixue.lite.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zhixue.lite.core.database.model.SubjectDiagnosisInfoEntity
import com.zhixue.lite.core.model.SubjectDiagnosisInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDiagnosisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjectDiagnosisInfoList(entities: List<SubjectDiagnosisInfoEntity>)

    @Query("SELECT report_id FROM subject_diagnosis WHERE report_id = :reportId ")
    fun getSubjectDiagnosisInfoStream(reportId: String): Flow<List<SubjectDiagnosisInfo>>
}
package com.zhixue.lite.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.zhixue.lite.core.database.model.SubjectDiagnosisInfoEntity

@Dao
interface SubjectDiagnosisInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjectDiagnosisEntities(entities: List<SubjectDiagnosisInfoEntity>)
}
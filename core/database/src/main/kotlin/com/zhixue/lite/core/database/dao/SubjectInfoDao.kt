package com.zhixue.lite.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zhixue.lite.core.database.model.SubjectInfoEntity

@Dao
interface SubjectInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubjectInfoEntities(subjectInfoEntities: List<SubjectInfoEntity>)

    @Query("SELECT * FROM SubjectInfoEntity WHERE reportId = :reportId")
    suspend fun getSubjectInfoEntities(reportId: String): List<SubjectInfoEntity>
}
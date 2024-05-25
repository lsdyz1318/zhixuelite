package com.zhixue.lite.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zhixue.lite.core.database.model.RemotePageEntity

@Dao
interface RemotePageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemotePageEntity(remotePageEntity: RemotePageEntity)

    @Query("SELECT * FROM RemotePageEntity WHERE label = :label")
    suspend fun getRemotePageEntity(label: String): RemotePageEntity?

    @Query("DELETE FROM RemotePageEntity WHERE label = :label")
    suspend fun deleteRemotePageEntity(label: String)
}
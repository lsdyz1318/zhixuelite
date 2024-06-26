package com.zhixue.lite.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zhixue.lite.core.database.model.RemotePageEntity

@Dao
interface RemotePageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemotePage(entity: RemotePageEntity)

    @Query(
        """
            SELECT next_page FROM remote_page
            WHERE user_id = :userId AND label = :label
        """
    )
    suspend fun getRemotePageNextPage(userId: String, label: String): Int?

    @Query(
        """
            DELETE FROM remote_page
            WHERE user_id = :userId AND label = :label
        """
    )
    suspend fun deleteRemotePage(userId: String, label: String)
}
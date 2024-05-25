package com.zhixue.lite.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zhixue.lite.core.database.dao.RemotePageDao
import com.zhixue.lite.core.database.dao.ReportInfoDao
import com.zhixue.lite.core.database.model.RemotePageEntity
import com.zhixue.lite.core.database.model.ReportInfoEntity

@Database(
    version = 1,
    entities = [RemotePageEntity::class, ReportInfoEntity::class]
)
internal abstract class ZhixueLiteDatabase : RoomDatabase() {
    abstract fun remotePageDao(): RemotePageDao
    abstract fun reportInfoDao(): ReportInfoDao
}
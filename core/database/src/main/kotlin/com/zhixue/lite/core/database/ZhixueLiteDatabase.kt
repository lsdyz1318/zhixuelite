package com.zhixue.lite.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zhixue.lite.core.database.dao.TrendInfoDao
import com.zhixue.lite.core.database.dao.RemotePageDao
import com.zhixue.lite.core.database.dao.ReportInfoDao
import com.zhixue.lite.core.database.dao.SubjectInfoDao
import com.zhixue.lite.core.database.model.TrendInfoEntity
import com.zhixue.lite.core.database.model.RemotePageEntity
import com.zhixue.lite.core.database.model.ReportInfoEntity
import com.zhixue.lite.core.database.model.SubjectInfoEntity

@Database(
    version = 1,
    entities = [
        RemotePageEntity::class,
        ReportInfoEntity::class,
        SubjectInfoEntity::class,
        TrendInfoEntity::class
    ]
)
internal abstract class ZhixueLiteDatabase : RoomDatabase() {
    abstract fun remotePageDao(): RemotePageDao
    abstract fun reportInfoDao(): ReportInfoDao
    abstract fun subjectInfoDao(): SubjectInfoDao
    abstract fun trendInfoDao(): TrendInfoDao
}
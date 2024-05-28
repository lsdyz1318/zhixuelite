package com.zhixue.lite.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zhixue.lite.core.database.dao.RemotePageDao
import com.zhixue.lite.core.database.dao.ReportInfoDao
import com.zhixue.lite.core.database.dao.PaperInfoDao
import com.zhixue.lite.core.database.dao.TrendInfoDao
import com.zhixue.lite.core.database.model.RemotePageEntity
import com.zhixue.lite.core.database.model.ReportInfoEntity
import com.zhixue.lite.core.database.model.PaperInfoEntity
import com.zhixue.lite.core.database.model.TrendInfoEntity
import com.zhixue.lite.core.database.util.IntListConverter

@Database(
    version = 1,
    entities = [
        RemotePageEntity::class,
        ReportInfoEntity::class,
        PaperInfoEntity::class,
        TrendInfoEntity::class
    ]
)
@TypeConverters(IntListConverter::class)
internal abstract class ZhixueLiteDatabase : RoomDatabase() {
    abstract fun remotePageDao(): RemotePageDao
    abstract fun reportInfoDao(): ReportInfoDao
    abstract fun paperInfoDao(): PaperInfoDao
    abstract fun trendInfoDao(): TrendInfoDao
}
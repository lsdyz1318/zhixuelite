package com.zhixue.lite.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zhixue.lite.core.database.dao.PaperDao
import com.zhixue.lite.core.database.dao.RemotePageDao
import com.zhixue.lite.core.database.dao.ReportDao
import com.zhixue.lite.core.database.dao.SubjectDiagnosisDao
import com.zhixue.lite.core.database.dao.TrendDao
import com.zhixue.lite.core.database.model.PaperInfoEntity
import com.zhixue.lite.core.database.model.RemotePageEntity
import com.zhixue.lite.core.database.model.ReportInfoEntity
import com.zhixue.lite.core.database.model.SubjectDiagnosisInfoEntity
import com.zhixue.lite.core.database.model.TrendInfoEntity
import com.zhixue.lite.core.database.util.IntListConverter

@Database(
    version = 1,
    entities = [
        RemotePageEntity::class,
        ReportInfoEntity::class,
        PaperInfoEntity::class,
        TrendInfoEntity::class,
        SubjectDiagnosisInfoEntity::class
    ]
)
@TypeConverters(IntListConverter::class)
internal abstract class ZhixueLiteDatabase : RoomDatabase() {
    abstract fun remotePageDao(): RemotePageDao
    abstract fun reportDao(): ReportDao
    abstract fun paperDap(): PaperDao
    abstract fun trendDao(): TrendDao
    abstract fun subjectDiagnosisDao(): SubjectDiagnosisDao
}
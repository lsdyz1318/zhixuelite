package com.zhixue.lite.core.database.di

import com.zhixue.lite.core.database.ZhixueLiteDatabase
import com.zhixue.lite.core.database.dao.RemotePageDao
import com.zhixue.lite.core.database.dao.ReportDao
import com.zhixue.lite.core.database.dao.PaperDao
import com.zhixue.lite.core.database.dao.SubjectDiagnosisDao
import com.zhixue.lite.core.database.dao.TrendDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun providesRemotePageDao(
        database: ZhixueLiteDatabase
    ): RemotePageDao = database.remotePageDao()

    @Provides
    fun providesReportDao(
        database: ZhixueLiteDatabase
    ): ReportDao = database.reportDao()

    @Provides
    fun providesPaperDao(
        database: ZhixueLiteDatabase
    ): PaperDao = database.paperDap()

    @Provides
    fun providesTrendDao(
        database: ZhixueLiteDatabase
    ): TrendDao = database.trendDao()

    @Provides
    fun providesSubjectDiagnosisDao(
        database: ZhixueLiteDatabase
    ): SubjectDiagnosisDao = database.subjectDiagnosisDao()
}
package com.zhixue.lite.core.database.di

import com.zhixue.lite.core.database.ZhixueLiteDatabase
import com.zhixue.lite.core.database.dao.RemotePageDao
import com.zhixue.lite.core.database.dao.ReportInfoDao
import com.zhixue.lite.core.database.dao.SubjectInfoDao
import com.zhixue.lite.core.database.dao.TrendInfoDao
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
    fun providesReportInfoDao(
        database: ZhixueLiteDatabase
    ): ReportInfoDao = database.reportInfoDao()

    @Provides
    fun providesSubjectInfoDao(
        database: ZhixueLiteDatabase
    ): SubjectInfoDao = database.subjectInfoDao()

    @Provides
    fun providesTrendInfoDao(
        database: ZhixueLiteDatabase
    ): TrendInfoDao = database.trendInfoDao()
}
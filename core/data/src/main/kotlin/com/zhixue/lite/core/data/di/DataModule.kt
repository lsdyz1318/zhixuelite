package com.zhixue.lite.core.data.di

import com.zhixue.lite.core.data.repository.PaperRepository
import com.zhixue.lite.core.data.repository.PaperRepositoryImpl
import com.zhixue.lite.core.data.repository.ReportRepository
import com.zhixue.lite.core.data.repository.ReportRepositoryImpl
import com.zhixue.lite.core.data.repository.UserRepository
import com.zhixue.lite.core.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {
    @Binds
    abstract fun bindsUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindsReportRepository(
        impl: ReportRepositoryImpl
    ): ReportRepository

    @Binds
    abstract fun bindsPaperRepository(
        impl: PaperRepositoryImpl
    ): PaperRepository
}
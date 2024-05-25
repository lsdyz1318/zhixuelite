package com.zhixue.lite.core.database.di

import android.content.Context
import androidx.room.Room
import com.zhixue.lite.core.database.ZhixueLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesZhixueLiteDatabase(
        @ApplicationContext
        context: Context
    ): ZhixueLiteDatabase = Room
        .databaseBuilder(context, ZhixueLiteDatabase::class.java, "zhixuelite-database")
        .build()
}
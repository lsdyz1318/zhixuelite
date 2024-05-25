package com.zhixue.lite.core.network.di

import com.zhixue.lite.core.network.NetworkDataSource
import com.zhixue.lite.core.network.retrofit.RetrofitNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface FlavoredNetworkModule {
    @Binds
    fun bindsNetworkDataSource(
        impl: RetrofitNetwork
    ): NetworkDataSource
}
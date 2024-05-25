package com.zhixue.lite.core.network.retrofit

import com.zhixue.lite.core.common.json.NetworkJson
import com.zhixue.lite.core.network.NetworkDataSource
import com.zhixue.lite.core.network.retrofit.api.ChangYanApi
import com.zhixue.lite.core.network.retrofit.api.ZhixueApi
import dagger.Lazy
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import javax.inject.Inject
import javax.inject.Singleton

private const val CHANG_YAN_BASE_URL = "https://open.changyan.com"
private const val ZHIXUE_BASE_URL = "https://www.zhixue.com"

@Singleton
internal class RetrofitNetwork @Inject constructor(
    @NetworkJson
    private val networkJson: Json,
    private val okHttpCallFactory: Lazy<Call.Factory>
) : NetworkDataSource {

    private val changYanApi: ChangYanApi = createNetworkApi(CHANG_YAN_BASE_URL)
    private val zhixueApi: ZhixueApi = createNetworkApi(ZHIXUE_BASE_URL)

    private inline fun <reified T> createNetworkApi(baseUrl: String): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .callFactory { okHttpCallFactory.get().newCall(it) }
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
            .create()
    }
}
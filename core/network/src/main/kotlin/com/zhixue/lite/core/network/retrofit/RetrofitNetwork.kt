package com.zhixue.lite.core.network.retrofit

import com.zhixue.lite.core.common.json.NetworkJson
import com.zhixue.lite.core.network.NetworkDataSource
import com.zhixue.lite.core.network.model.NetworkCasInfo
import com.zhixue.lite.core.network.model.NetworkLevelTrend
import com.zhixue.lite.core.network.model.NetworkReportInfoPage
import com.zhixue.lite.core.network.model.NetworkReportMain
import com.zhixue.lite.core.network.model.NetworkSsoInfo
import com.zhixue.lite.core.network.model.NetworkUserInfo
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

    override suspend fun ssoLogin(
        username: String, password: String, captcha: String
    ): NetworkSsoInfo {
        return changYanApi.ssoLogin(username, password, captcha).data
    }

    override suspend fun ssoLogin(ticket: String): NetworkSsoInfo {
        return changYanApi.ssoLogin(ticket).data
    }

    override suspend fun casLogin(at: String, userId: String): NetworkCasInfo {
        return zhixueApi.casLogin(at, userId).result!!
    }

    override suspend fun getUserInfo(token: String): NetworkUserInfo {
        return zhixueApi.getUserInfo(token).result!!
    }

    override suspend fun getReportInfoPage(
        reportType: String, page: Int, token: String
    ): NetworkReportInfoPage {
        return zhixueApi.getReportInfoPage(reportType, page, token).result!!
    }

    override suspend fun getReportMain(reportId: String, token: String): NetworkReportMain {
        return zhixueApi.getReportMain(reportId, token).result!!
    }

    override suspend fun getLevelTrend(
        reportId: String, subjectId: String, token: String
    ): NetworkLevelTrend {
        return zhixueApi.getLevelTrend(reportId, subjectId, token).result!!
    }

    private inline fun <reified T> createNetworkApi(baseUrl: String): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .callFactory { okHttpCallFactory.get().newCall(it) }
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
            .create()
    }
}
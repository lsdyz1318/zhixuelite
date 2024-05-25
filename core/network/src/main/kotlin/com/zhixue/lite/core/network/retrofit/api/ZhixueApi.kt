package com.zhixue.lite.core.network.retrofit.api

import com.zhixue.lite.core.network.model.NetworkCasInfo
import com.zhixue.lite.core.network.model.NetworkReportInfoPage
import com.zhixue.lite.core.network.model.NetworkUserInfo
import com.zhixue.lite.core.network.retrofit.model.ZhixueNetworkResponse
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

internal interface ZhixueApi {
    @FormUrlEncoded
    @POST("container/app/login/casLogin")
    suspend fun casLogin(
        @Field("at")
        at: String,
        @Field("userId")
        userId: String
    ): ZhixueNetworkResponse<NetworkCasInfo>

    @FormUrlEncoded
    @POST("zhixuebao/base/common/getUserInfo")
    suspend fun getUserInfo(
        @Field("token")
        token: String
    ): ZhixueNetworkResponse<NetworkUserInfo>

    @FormUrlEncoded
    @POST("zxbReport/report/getPageAllExamList")
    suspend fun getReportInfoPage(
        @Field("reportType")
        type: String,
        @Field("pageIndex")
        page: Int,
        @Field("token")
        token: String,
        @FieldMap
        fields: Map<String, String> = mapOf(
            "actualPosition" to "0",
            "pageSize" to "10"
        )
    ): ZhixueNetworkResponse<NetworkReportInfoPage>
}
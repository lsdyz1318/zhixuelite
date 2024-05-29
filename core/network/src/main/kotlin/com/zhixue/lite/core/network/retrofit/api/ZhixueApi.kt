package com.zhixue.lite.core.network.retrofit.api

import com.zhixue.lite.core.network.model.NetworkCasInfo
import com.zhixue.lite.core.network.model.NetworkLevelTrend
import com.zhixue.lite.core.network.model.NetworkReportInfoPage
import com.zhixue.lite.core.network.model.NetworkReportMain
import com.zhixue.lite.core.network.model.NetworkSubjectDiagnosis
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
        accessTicket: String,
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
        reportType: String,
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

    @FormUrlEncoded
    @POST("zxbReport/report/exam/getSubjectDiagnosis")
    suspend fun getSubjectDiagnosis(
        @Field("examId")
        reportId: String,
        @Field("token")
        token: String
    ): ZhixueNetworkResponse<NetworkSubjectDiagnosis>

    @FormUrlEncoded
    @POST("zxbReport/report/exam/getReportMain")
    suspend fun getReportMain(
        @Field("examId")
        reportId: String,
        @Field("token")
        token: String
    ): ZhixueNetworkResponse<NetworkReportMain>

    @FormUrlEncoded
    @POST("zxbReport/report/paper/getLevelTrend")
    suspend fun getLevelTrend(
        @Field("examId")
        reportId: String,
        @Field("paperId")
        paperId: String,
        @Field("token")
        token: String,
        @FieldMap
        fields: Map<String, String> = mapOf(
            "pageSize" to "5",
            "pageIndex" to "1"
        )
    ): ZhixueNetworkResponse<NetworkLevelTrend>
}
package com.zhixue.lite.core.network.retrofit.api

import com.zhixue.lite.core.network.model.NetworkSsoInfo
import com.zhixue.lite.core.network.retrofit.model.ChangYanNetworkResponse
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

internal interface ChangYanApi {
    @FormUrlEncoded
    @POST("sso/v1/api")
    suspend fun ssoLogin(
        @Field("username")
        username: String,
        @Field("password")
        password: String,
        @Field("thirdCaptchaParam")
        captcha: String,
        @FieldMap
        fields: Map<String, String> = mapOf(
            "appId" to "zhixue_parent",
            "captchaType" to "third",
            "client" to "android",
            "encode" to "true",
            "encodeType" to "R2/P",
            "extInfo" to "{\"deviceId\":\"0\"}",
            "key" to "auto",
            "method" to "sso.login.account.v3"
        )
    ): ChangYanNetworkResponse<NetworkSsoInfo>

    @FormUrlEncoded
    @POST("sso/v1/api")
    suspend fun ssoLogin(
        @Field("tgt")
        grantTicket: String,
        @FieldMap
        fields: Map<String, String> = mapOf(
            "appId" to "zhixue_parent",
            "client" to "android",
            "extInfo" to "{\"deviceId\":\"0\"}",
            "method" to "sso.extend.tgt"
        )
    ): ChangYanNetworkResponse<NetworkSsoInfo>
}
package com.zhixue.lite.core.network

import com.zhixue.lite.core.network.model.NetworkCasInfo
import com.zhixue.lite.core.network.model.NetworkReportInfoPage
import com.zhixue.lite.core.network.model.NetworkSsoInfo
import com.zhixue.lite.core.network.model.NetworkSubjectInfo
import com.zhixue.lite.core.network.model.NetworkTrendInfo
import com.zhixue.lite.core.network.model.NetworkUserInfo

interface NetworkDataSource {

    suspend fun ssoLogin(username: String, password: String, captcha: String): NetworkSsoInfo

    suspend fun ssoLogin(userTicket: String): NetworkSsoInfo

    suspend fun casLogin(at: String, userId: String): NetworkCasInfo

    suspend fun getUserInfo(userToken: String): NetworkUserInfo

    suspend fun getReportInfoPage(
        reportType: String, page: Int, userToken: String
    ): NetworkReportInfoPage

    suspend fun getSubjectInfoList(reportId: String, userToken: String): List<NetworkSubjectInfo>

    suspend fun getTrendInfoList(
        reportId: String, subjectId: String, userToken: String
    ): List<NetworkTrendInfo>
}
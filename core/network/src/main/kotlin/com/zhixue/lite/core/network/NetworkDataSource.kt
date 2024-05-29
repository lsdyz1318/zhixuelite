package com.zhixue.lite.core.network

import com.zhixue.lite.core.network.model.NetworkCasInfo
import com.zhixue.lite.core.network.model.NetworkPaperInfo
import com.zhixue.lite.core.network.model.NetworkReportInfoPage
import com.zhixue.lite.core.network.model.NetworkSsoInfo
import com.zhixue.lite.core.network.model.NetworkSubjectDiagnosisInfo
import com.zhixue.lite.core.network.model.NetworkTrendInfo
import com.zhixue.lite.core.network.model.NetworkUserInfo

interface NetworkDataSource {

    suspend fun ssoLogin(username: String, password: String, captcha: String): NetworkSsoInfo

    suspend fun ssoLogin(grantTicket: String): NetworkSsoInfo

    suspend fun casLogin(accessTicket: String, userId: String): NetworkCasInfo

    suspend fun getUserInfo(token: String): NetworkUserInfo

    suspend fun getReportInfoPage(
        reportType: String, page: Int, token: String
    ): NetworkReportInfoPage

    suspend fun getSubjectDiagnosisInfoList(
        reportId: String, token: String
    ): List<NetworkSubjectDiagnosisInfo>

    suspend fun getPaperInfoList(reportId: String, token: String): List<NetworkPaperInfo>

    suspend fun getTrendInfoList(
        reportId: String, paperId: String, token: String
    ): List<NetworkTrendInfo>
}
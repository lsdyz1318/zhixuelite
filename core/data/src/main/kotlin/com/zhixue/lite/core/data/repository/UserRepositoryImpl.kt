package com.zhixue.lite.core.data.repository

import com.auth0.android.jwt.JWT
import com.zhixue.lite.core.datastore.PreferencesDataSource
import com.zhixue.lite.core.datastore.model.UserPreferences
import com.zhixue.lite.core.datastore.model.asExternalModel
import com.zhixue.lite.core.model.UserData
import com.zhixue.lite.core.network.NetworkDataSource
import com.zhixue.lite.core.network.model.NetworkSsoInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val preferencesDataSource: PreferencesDataSource
) : UserRepository {

    private val userData: Flow<UserData> = preferencesDataSource.userPreferences
        .map(UserPreferences::asExternalModel)

    override suspend fun userLogin(username: String, password: String, captcha: String) {
        handleLogin(ssoInfo = networkDataSource.ssoLogin(username, password, captcha))
    }

    override suspend fun autoLogin(ticket: String) {
        handleLogin(ssoInfo = networkDataSource.ssoLogin(ticket))
    }

    override suspend fun getUserId(): String {
        return userData.first().id
    }

    override suspend fun getUserToken(): String {
        var token = userData.first().token
        if (JWT(token).isExpired(0)) {
            token = refreshUserToken()
        }
        return token
    }

    private suspend fun handleLogin(ssoInfo: NetworkSsoInfo) {
        val casInfo = networkDataSource.casLogin(ssoInfo.at, ssoInfo.userId)
        val userInfo = networkDataSource.getUserInfo(casInfo.token)

        preferencesDataSource.setUserId(userInfo.curUserId)
        preferencesDataSource.setUserToken(casInfo.token)
        preferencesDataSource.setUserTicket(ssoInfo.ticket)
    }

    private suspend fun refreshUserToken(): String {
        autoLogin(ticket = userData.first().ticket)
        return userData.first().token
    }
}
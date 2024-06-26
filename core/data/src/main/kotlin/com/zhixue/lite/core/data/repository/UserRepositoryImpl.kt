package com.zhixue.lite.core.data.repository

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

    private val userDataFlow: Flow<UserData> = preferencesDataSource.userPreferences
        .map(UserPreferences::asExternalModel)

    override var userId: String = ""
        private set

    override var token: String = ""
        private set

    override suspend fun userLogin(username: String, password: String, captcha: String) {
        handleLogin(networkDataSource.ssoLogin(username, password, captcha))
    }

    override suspend fun userLogin(grantTicket: String) {
        handleLogin(networkDataSource.ssoLogin(grantTicket))
    }

    override suspend fun getGrantTicket(): String {
        return userDataFlow.first().grantTicket
    }

    private suspend fun handleLogin(ssoInfo: NetworkSsoInfo) {
        val casInfo = networkDataSource.casLogin(ssoInfo.accessTicket, ssoInfo.userId)
        val token = casInfo.token
        val currentUserId = networkDataSource.getUserInfo(token).currentUserId

        this.userId = currentUserId
        this.token = token

        preferencesDataSource.setUserId(currentUserId)
        preferencesDataSource.setGrantTicket(ssoInfo.grantTicket)
    }
}
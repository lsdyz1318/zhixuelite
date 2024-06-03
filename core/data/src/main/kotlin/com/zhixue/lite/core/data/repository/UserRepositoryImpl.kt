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
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull
import javax.inject.Inject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

internal class UserRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val preferencesDataSource: PreferencesDataSource
) : UserRepository {

    private val userDataFlow: Flow<UserData> = preferencesDataSource.userPreferences
        .map(UserPreferences::asExternalModel)

    override var userId: String = ""
        private set

    override suspend fun userLogin(username: String, password: String, captcha: String) {
        handleLogin(networkDataSource.ssoLogin(username, password, captcha))
    }

    override suspend fun userLogin(grantTicket: String) {
        handleLogin(networkDataSource.ssoLogin(grantTicket))
    }

    override suspend fun getToken(): String {
        val userData = userDataFlow.first()
        var token = userData.token
        // 如果令牌过期，则重新登录
        if (isTokenExpired(token)) {
            userLogin(userData.grantTicket)
            token = userDataFlow.first().token
        }
        return token
    }

    override suspend fun getGrantTicket(): String {
        return userDataFlow.first().grantTicket
    }

    private suspend fun handleLogin(ssoInfo: NetworkSsoInfo) {
        val casInfo = networkDataSource.casLogin(ssoInfo.accessTicket, ssoInfo.userId)
        val currentUserId = networkDataSource.getUserInfo(casInfo.token).currentUserId

        userId = currentUserId

        preferencesDataSource.setUserId(currentUserId)
        preferencesDataSource.setToken(casInfo.token)
        preferencesDataSource.setGrantTicket(ssoInfo.grantTicket)
    }

    @OptIn(ExperimentalEncodingApi::class)
    private fun isTokenExpired(token: String): Boolean {
        val payload = token.split(".")[1]
        val decodePayload = String(Base64.decode(payload))
        val payloadElement = Json.parseToJsonElement(decodePayload).jsonObject
        val exp = payloadElement["exp"]?.jsonPrimitive?.longOrNull

        return exp != null && exp > System.currentTimeMillis()
    }
}
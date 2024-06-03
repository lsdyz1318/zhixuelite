package com.zhixue.lite.core.data.repository

interface UserRepository {

    val userId: String

    suspend fun userLogin(username: String, password: String, captcha: String)

    suspend fun userLogin(grantTicket: String)

    suspend fun getToken(): String

    suspend fun getGrantTicket(): String
}
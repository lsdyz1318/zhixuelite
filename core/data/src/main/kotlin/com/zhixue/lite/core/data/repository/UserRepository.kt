package com.zhixue.lite.core.data.repository

interface UserRepository {

    val userId: String

    val token: String

    suspend fun userLogin(username: String, password: String, captcha: String)

    suspend fun userLogin(grantTicket: String)

    suspend fun getGrantTicket(): String
}
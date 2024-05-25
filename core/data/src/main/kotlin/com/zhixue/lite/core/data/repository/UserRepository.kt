package com.zhixue.lite.core.data.repository

interface UserRepository {

    suspend fun userLogin(username: String, password: String, captcha: String)

    suspend fun autoLogin(ticket: String)

    suspend fun getUserId(): String

    suspend fun getUserToken(): String
}
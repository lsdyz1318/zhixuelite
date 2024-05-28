package com.zhixue.lite.core.datastore.model

import com.zhixue.lite.core.model.UserData
import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val token: String = "",
    val userId: String = "",
    val grantTicket: String = ""
)

fun UserPreferences.asExternalModel(): UserData = UserData(
    token = token,
    userId = userId,
    grantTicket = grantTicket
)
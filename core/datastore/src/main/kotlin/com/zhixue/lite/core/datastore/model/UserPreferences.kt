package com.zhixue.lite.core.datastore.model

import com.zhixue.lite.core.model.UserData
import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val userId: String = "",
    val userToken: String = "",
    val userTicket: String = ""
)

fun UserPreferences.asExternalModel(): UserData = UserData(
    userId = userId,
    userToken = userToken,
    userTicket = userTicket
)
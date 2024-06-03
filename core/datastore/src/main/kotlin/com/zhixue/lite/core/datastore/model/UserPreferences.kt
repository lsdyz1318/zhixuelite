package com.zhixue.lite.core.datastore.model

import com.zhixue.lite.core.model.UserData
import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val id: String = "",
    val token: String = "",
    val grantTicket: String = ""
)

fun UserPreferences.asExternalModel(): UserData = UserData(
    id = id,
    token = token,
    grantTicket = grantTicket
)
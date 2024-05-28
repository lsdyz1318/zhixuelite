package com.zhixue.lite.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSsoInfo(
    @SerialName("userId")
    val userId: String,
    @SerialName("tgt")
    val grantTicket: String,
    @SerialName("at")
    val accessTicket: String
)
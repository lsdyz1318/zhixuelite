package com.zhixue.lite.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSsoInfo(
    @SerialName("at")
    val at: String,
    @SerialName("userId")
    val userId: String,
    @SerialName("tgt")
    val ticket: String
)
package com.zhixue.lite.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCasInfo(
    @SerialName("token")
    val token: String
)
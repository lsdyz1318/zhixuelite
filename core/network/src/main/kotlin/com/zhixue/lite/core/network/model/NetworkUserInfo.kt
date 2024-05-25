package com.zhixue.lite.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkUserInfo(
    @SerialName("curChildId")
    val curUserId: String
)
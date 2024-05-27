package com.zhixue.lite.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkLevelTrend(
    @SerialName("list")
    val levelTrendInfoList: List<NetworkLevelTrendInfo>
)

@Serializable
data class NetworkLevelTrendInfo(
    @SerialName("improveBar")
    val improveInfo: ImproveInfo
) {
    @Serializable
    data class ImproveInfo(
        @SerialName("levelScale")
        val level: String
    )
}
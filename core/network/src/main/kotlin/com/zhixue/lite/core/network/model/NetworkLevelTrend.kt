package com.zhixue.lite.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkLevelTrend(
    @SerialName("list")
    val trendInfoList: List<NetworkTrendInfo>
)

@Serializable
data class NetworkTrendInfo(
    @SerialName("improveBar")
    val improveInfo: ImproveInfo
) {
    @Serializable
    data class ImproveInfo(
        @SerialName("levelScale")
        val level: String
    )
}
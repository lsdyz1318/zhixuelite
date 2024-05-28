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
    @SerialName("tag")
    val tag: Tag,
    @SerialName("improveBar")
    val improveInfo: ImproveInfo,
    @SerialName("dataList")
    val dataList: List<TrendData>,
    @SerialName("totalNum")
    val studentNumber: Int
) {
    @Serializable
    data class Tag(
        @SerialName("code")
        val code: String,
        @SerialName("name")
        val name: String
    )

    @Serializable
    data class ImproveInfo(
        @SerialName("tag")
        val tag: Tag,
        @SerialName("levelScale")
        val level: String,
        @SerialName("offset")
        val offset: Int
    )

    @Serializable
    data class TrendData(
        @SerialName("improveBar")
        val improveInfo: ImproveInfo
    )
}
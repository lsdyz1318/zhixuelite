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
    @SerialName("dataList")
    val dataList: List<TrendData>
) {
    @Serializable
    data class Tag(
        @SerialName("code")
        val code: String,
        @SerialName("name")
        val name: String
    )

    @Serializable
    data class TrendData(
        @SerialName("id")
        val paperId: String,
        @SerialName("name")
        val paperName: String,
        @SerialName("dateTime")
        val datetime: Long,
        @SerialName("level")
        val level: String,
        @SerialName("totalNum")
        val studentNumber: Int,
        @SerialName("improveBar")
        val improveInfo: ImproveInfo
    )

    @Serializable
    data class ImproveInfo(
        @SerialName("tag")
        val tag: Tag,
        @SerialName("offset")
        val offset: Int
    )
}
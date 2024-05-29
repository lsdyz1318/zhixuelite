package com.zhixue.lite.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkReportInfoPage(
    @SerialName("hasNextPage")
    val hasNextPage: Boolean,
    @SerialName("examInfoList")
    val reportInfoList: List<NetworkReportInfo>
)

@Serializable
data class NetworkReportInfo(
    @SerialName("examId")
    val reportId: String,
    @SerialName("examName")
    val reportName: String,
    @SerialName("examCreateDateTime")
    val datetime: Long,
    @SerialName("isSinglePublish")
    val isPublished: Boolean = true
)
package com.zhixue.lite.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkReportMain(
    @SerialName("paperList")
    val subjectList: List<NetworkSubjectInfo>
)

@Serializable
data class NetworkSubjectInfo(
    @SerialName("paperId")
    val subjectId: String,
    @SerialName("subjectName")
    val subjectName: String,
    @SerialName("userScore")
    val userScore: Double,
    @SerialName("standardScore")
    val standardScore: Double
)
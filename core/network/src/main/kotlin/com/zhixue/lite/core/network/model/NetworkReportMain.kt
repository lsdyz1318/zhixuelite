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
    val id: String,
    @SerialName("subjectName")
    val name: String,
    @SerialName("userScore")
    val userScore: Double,
    @SerialName("standardScore")
    val standardScore: Double
)
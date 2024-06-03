package com.zhixue.lite.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkReportMain(
    @SerialName("paperList")
    val paperInfoList: List<NetworkPaperInfo>
)

@Serializable
data class NetworkPaperInfo(
    @SerialName("paperId")
    val id: String,
    @SerialName("subjectCode")
    val subjectCode: String,
    @SerialName("subjectName")
    val subjectName: String,
    @SerialName("userScore")
    val userScore: Double? = null,
    @SerialName("standardScore")
    val standardScore: Double,
    @SerialName("clazzRank")
    val classRank: Int? = null
)
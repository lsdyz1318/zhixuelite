package com.zhixue.lite.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSubjectDiagnosis(
    @SerialName("list")
    val subjectDiagnosisInfoList: List<NetworkSubjectDiagnosisInfo>
)

@Serializable
data class NetworkSubjectDiagnosisInfo(
    @SerialName("subjectCode")
    val subjectCode: String,
    @SerialName("myRank")
    val userLevel: Double,
    @SerialName("avgRank")
    val averageLevel: Double
)
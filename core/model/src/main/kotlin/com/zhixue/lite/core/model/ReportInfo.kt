package com.zhixue.lite.core.model

data class ReportInfo(
    val id: String,
    val name: String,
    val createDate: Long,
    val isPublished: Boolean
)

data class FormatReportInfo(
    val id: String,
    val name: String,
    val createDate: String,
    val isPublished: Boolean
)
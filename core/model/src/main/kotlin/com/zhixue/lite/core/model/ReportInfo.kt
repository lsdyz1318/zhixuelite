package com.zhixue.lite.core.model

data class ReportInfo(
    val reportId: String,
    val reportName: String,
    val datetime: Long,
    val isPublished: Boolean
)

data class FormatReportInfo(
    val reportId: String,
    val reportName: String,
    val datetime: String,
    val isPublished: Boolean
)
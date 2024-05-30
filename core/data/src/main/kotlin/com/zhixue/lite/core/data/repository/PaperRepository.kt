package com.zhixue.lite.core.data.repository

interface PaperRepository {

    suspend fun getPaperInfoIds(reportId: String): List<String>

    suspend fun syncPaperInfoList(reportId: String)

    suspend fun syncTrendInfoList(reportId: String, paperId: String)
}
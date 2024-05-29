package com.zhixue.lite.core.data.repository

interface PaperRepository {

    suspend fun syncPaperInfo(reportId: String)

    suspend fun syncTrendInfo(reportId: String, paperId: String)

    suspend fun getPaperInfoIds(reportId: String): List<String>
}
package com.zhixue.lite.core.data.repository

import com.zhixue.lite.core.model.PaperInfo

interface PaperRepository {

    suspend fun getPaperInfoIds(reportId: String): List<String>

    suspend fun getPaperInfoList(reportId: String): List<PaperInfo>

    suspend fun syncPaperInfoList(reportId: String)

    suspend fun syncTrendInfoList(reportId: String, paperId: String)
}
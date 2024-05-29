package com.zhixue.lite.core.data.repository

import com.zhixue.lite.core.model.PaperInfo
import kotlinx.coroutines.flow.Flow

interface PaperRepository {

    suspend fun syncPaperInfo(reportId: String)

    suspend fun syncTrendInfo(reportId: String, paperId: String)

    suspend fun getPaperInfoIds(reportId: String): List<String>

    fun getPaperInfoStream(reportId: String): Flow<List<PaperInfo>>
}
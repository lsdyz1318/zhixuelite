package com.zhixue.lite.core.data.repository

import com.zhixue.lite.core.model.PaperInfo
import kotlinx.coroutines.flow.Flow

interface PaperRepository {

    fun getPaperInfoList(reportId: String): Flow<List<PaperInfo>>

    suspend fun getPaperInfoIds(reportId: String): List<String>

    suspend fun syncPaperInfoList(reportId: String)

    suspend fun syncTrendInfoList(reportId: String, paperId: String)
}
package com.zhixue.lite.core.data.repository

import androidx.paging.PagingData
import com.zhixue.lite.core.model.ReportInfo
import com.zhixue.lite.core.model.PaperInfo
import com.zhixue.lite.core.model.TrendInfo
import kotlinx.coroutines.flow.Flow

interface ReportRepository {

    fun getReportInfoList(reportType: String): Flow<PagingData<ReportInfo>>

    suspend fun getPaperInfoList(reportId: String): List<PaperInfo>

    suspend fun getTrendInfoList(reportId: String, paperId: String): List<TrendInfo>
}
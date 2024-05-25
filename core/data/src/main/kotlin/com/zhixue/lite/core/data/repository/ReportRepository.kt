package com.zhixue.lite.core.data.repository

import androidx.paging.PagingData
import com.zhixue.lite.core.model.ReportInfo
import kotlinx.coroutines.flow.Flow

interface ReportRepository {

    fun getReportInfoList(type: String): Flow<PagingData<ReportInfo>>
}
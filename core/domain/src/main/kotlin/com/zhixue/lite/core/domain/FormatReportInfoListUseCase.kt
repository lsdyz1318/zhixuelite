package com.zhixue.lite.core.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.zhixue.lite.core.data.repository.ReportRepository
import com.zhixue.lite.core.model.FormatReportInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class FormatReportInfoListUseCase @Inject constructor(
    private val reportRepository: ReportRepository
) {
    private val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    operator fun invoke(reportType: String): Flow<PagingData<FormatReportInfo>> {
        return reportRepository.getReportInfoList(reportType).map { pagingData ->
            pagingData.map {
                FormatReportInfo(
                    reportId = it.reportId,
                    reportName = it.reportName,
                    dateTime = formatter.format(it.dateTime),
                    isPublished = it.isPublished
                )
            }
        }
    }
}
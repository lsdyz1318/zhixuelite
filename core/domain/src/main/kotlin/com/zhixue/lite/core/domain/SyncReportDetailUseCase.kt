package com.zhixue.lite.core.domain

import com.zhixue.lite.core.data.repository.PaperRepository
import com.zhixue.lite.core.data.repository.ReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SyncReportDetailUseCase @Inject constructor(
    private val reportRepository: ReportRepository,
    private val paperRepository: PaperRepository
) {
    suspend operator fun invoke(reportId: String) = withContext(Dispatchers.IO) {
        reportRepository.syncSubjectDiagnosisInfo(reportId)
        paperRepository.syncPaperInfo(reportId)
        paperRepository.getPaperInfoIds(reportId)
            .map { paperId ->
                async { paperRepository.syncTrendInfo(reportId, paperId) }
            }
            .awaitAll()
    }
}
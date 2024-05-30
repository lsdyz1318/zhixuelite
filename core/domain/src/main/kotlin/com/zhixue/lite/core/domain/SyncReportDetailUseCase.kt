package com.zhixue.lite.core.domain

import com.zhixue.lite.core.data.repository.PaperRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SyncReportDetailUseCase @Inject constructor(
    private val paperRepository: PaperRepository
) {
    suspend operator fun invoke(reportId: String) {
        withContext(Dispatchers.IO) {
            paperRepository.syncPaperInfoList(reportId)
            paperRepository.getPaperInfoIds(reportId)
                .map { paperId ->
                    async { paperRepository.syncTrendInfoList(reportId, paperId) }
                }
                .awaitAll()
        }
    }
}
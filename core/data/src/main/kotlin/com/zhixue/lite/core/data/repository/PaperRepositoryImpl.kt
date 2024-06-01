package com.zhixue.lite.core.data.repository

import com.zhixue.lite.core.data.model.asEntity
import com.zhixue.lite.core.data.model.mapToTrendInfoEntities
import com.zhixue.lite.core.database.dao.PaperInfoDao
import com.zhixue.lite.core.database.dao.TrendInfoDao
import com.zhixue.lite.core.database.model.PopulatedPaperInfo
import com.zhixue.lite.core.database.model.asExternalModel
import com.zhixue.lite.core.model.PaperInfo
import com.zhixue.lite.core.network.NetworkDataSource
import com.zhixue.lite.core.network.model.NetworkTrendInfo
import javax.inject.Inject

internal class PaperRepositoryImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val paperInfoDao: PaperInfoDao,
    private val trendInfoDao: TrendInfoDao,
    private val networkDataSource: NetworkDataSource
) : PaperRepository {

    override suspend fun getPaperInfoList(reportId: String): List<PaperInfo> {
        return paperInfoDao.getPaperInfoList(reportId).map(PopulatedPaperInfo::asExternalModel)
    }

    override suspend fun getPaperInfoIds(reportId: String): List<String> {
        return paperInfoDao.getPaperInfoIds(reportId)
    }

    override suspend fun syncPaperInfoList(reportId: String) {
        try {
            val networkPaperInfoList = networkDataSource.getPaperInfoList(
                reportId = reportId,
                token = userRepository.getToken()
            )

            val networkSubjectDiagnosisList = runCatching {
                networkDataSource.getSubjectDiagnosisInfoList(
                    reportId = reportId,
                    token = userRepository.getToken()
                )
            }.getOrNull()

            networkPaperInfoList
                .map { networkPaperInfo ->
                    networkPaperInfo.asEntity(
                        reportId = reportId,
                        classPercentile = networkSubjectDiagnosisList
                            ?.find { it.subjectCode == networkPaperInfo.subjectCode }
                            ?.classPercentile
                    )
                }
                .run {
                    paperInfoDao.insertPaperInfoList(this)
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun syncTrendInfoList(reportId: String, paperId: String) {
        try {
            networkDataSource.getTrendInfoList(
                reportId = reportId,
                paperId = paperId,
                token = userRepository.getToken()
            )
                .flatMap(NetworkTrendInfo::mapToTrendInfoEntities)
                .run { trendInfoDao.insertTrendInfoList(this) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
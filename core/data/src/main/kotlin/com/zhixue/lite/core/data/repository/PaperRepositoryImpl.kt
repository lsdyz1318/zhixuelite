package com.zhixue.lite.core.data.repository

import com.zhixue.lite.core.data.model.asEntity
import com.zhixue.lite.core.database.dao.PaperDao
import com.zhixue.lite.core.database.dao.TrendDao
import com.zhixue.lite.core.network.NetworkDataSource
import javax.inject.Inject

class PaperRepositoryImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val paperDao: PaperDao,
    private val trendDao: TrendDao,
    private val networkDataSource: NetworkDataSource
) : PaperRepository {

    override suspend fun syncPaperInfo(reportId: String) {
        try {
            val paperInfoEntities = networkDataSource.getPaperInfoList(
                reportId = reportId,
                token = userRepository.getToken()
            ).map {
                it.asEntity(reportId)
            }
            paperDao.insertPaperInfoList(paperInfoEntities)
        } catch (_: Exception) {
        }
    }

    override suspend fun syncTrendInfo(reportId: String, paperId: String) {
        try {
            val trendInfoEntities = networkDataSource.getTrendInfoList(
                reportId = reportId,
                paperId = paperId,
                token = userRepository.getToken()
            ).map {
                it.asEntity(reportId)
            }
            trendDao.insertTrendInfoList(trendInfoEntities)
        } catch (_: Exception) {
        }
    }

    override suspend fun getPaperInfoIds(reportId: String): List<String> {
        return paperDao.getPaperInfoIds(reportId)
    }
}
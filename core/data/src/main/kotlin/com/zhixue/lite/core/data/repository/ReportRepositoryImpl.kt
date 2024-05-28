package com.zhixue.lite.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.zhixue.lite.core.data.model.asEntity
import com.zhixue.lite.core.database.dao.PaperInfoDao
import com.zhixue.lite.core.database.dao.RemotePageDao
import com.zhixue.lite.core.database.dao.ReportInfoDao
import com.zhixue.lite.core.database.dao.TrendInfoDao
import com.zhixue.lite.core.database.model.ReportInfoEntity
import com.zhixue.lite.core.database.model.asExternalModel
import com.zhixue.lite.core.model.ReportInfo
import com.zhixue.lite.core.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val remotePageDao: RemotePageDao,
    private val reportInfoDao: ReportInfoDao,
    private val paperInfoDao: PaperInfoDao,
    private val trendInfoDao: TrendInfoDao,
    private val networkDataSource: NetworkDataSource
) : ReportRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getReportInfoList(reportType: String): Flow<PagingData<ReportInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            remoteMediator = ReportInfoRemoteMediator(
                reportType = reportType,
                userRepository = userRepository,
                remotePageDao = remotePageDao,
                reportInfoDao = reportInfoDao,
                networkDataSource = networkDataSource
            ),
            pagingSourceFactory = {
                reportInfoDao.getReportInfoPagingSource(reportType)
            }
        ).flow.map { pagingData ->
            pagingData.map(ReportInfoEntity::asExternalModel)
        }
    }

    override suspend fun syncPaperInfo(reportId: String) {
        try {
            val paperInfoEntities = networkDataSource.getPaperInfoList(
                reportId = reportId,
                token = userRepository.getToken()
            ).map {
                it.asEntity(reportId)
            }
            paperInfoDao.insertPaperInfoEntities(paperInfoEntities)
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
            trendInfoDao.insertTrendInfoEntities(trendInfoEntities)
        } catch (_: Exception) {
        }
    }
}
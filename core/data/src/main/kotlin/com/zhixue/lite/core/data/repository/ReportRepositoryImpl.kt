package com.zhixue.lite.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.zhixue.lite.core.data.model.asEntity
import com.zhixue.lite.core.database.dao.RemotePageDao
import com.zhixue.lite.core.database.dao.ReportDao
import com.zhixue.lite.core.database.dao.SubjectDiagnosisDao
import com.zhixue.lite.core.database.model.ReportInfoEntity
import com.zhixue.lite.core.database.model.asExternalModel
import com.zhixue.lite.core.model.ReportInfo
import com.zhixue.lite.core.model.SubjectDiagnosisInfo
import com.zhixue.lite.core.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val remotePageDao: RemotePageDao,
    private val reportDao: ReportDao,
    private val subjectDiagnosisDao: SubjectDiagnosisDao,
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
                reportDao = reportDao,
                networkDataSource = networkDataSource
            ),
            pagingSourceFactory = {
                reportDao.getReportInfoPagingSource(reportType)
            }
        ).flow.map { pagingData ->
            pagingData.map(ReportInfoEntity::asExternalModel)
        }
    }

    override suspend fun syncSubjectDiagnosisInfo(reportId: String) {
        try {
            val subjectDiagnosisInfoEntities = networkDataSource.getSubjectDiagnosisInfoList(
                reportId = reportId,
                token = userRepository.getToken()
            ).map {
                it.asEntity(reportId)
            }
            subjectDiagnosisDao.insertSubjectDiagnosisInfoList(subjectDiagnosisInfoEntities)
        } catch (_: Exception) {
        }
    }

    override fun getSubjectDiagnosisInfoStream(reportId: String): Flow<List<SubjectDiagnosisInfo>> {
        return subjectDiagnosisDao.getSubjectDiagnosisInfoStream(reportId)
    }
}
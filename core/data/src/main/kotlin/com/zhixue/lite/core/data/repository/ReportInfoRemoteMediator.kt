package com.zhixue.lite.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.zhixue.lite.core.database.dao.RemotePageDao
import com.zhixue.lite.core.database.dao.ReportInfoDao
import com.zhixue.lite.core.database.model.RemotePageEntity
import com.zhixue.lite.core.database.model.ReportInfoEntity
import com.zhixue.lite.core.network.NetworkDataSource

private const val LABEL_PREFIX = "REPORT_INFO"
private const val STARTING_PAGE = 1

@OptIn(ExperimentalPagingApi::class)
internal class ReportInfoRemoteMediator(
    private val reportType: String,
    private val userRepository: UserRepository,
    private val remotePageDao: RemotePageDao,
    private val reportInfoDao: ReportInfoDao,
    private val networkDataSource: NetworkDataSource
) : RemoteMediator<Int, ReportInfoEntity>() {

    override suspend fun initialize(): InitializeAction {
        return try {
            val localLatestId = reportInfoDao.getReportInfoIds(reportType)
            val networkLatestId = networkDataSource.getReportInfoPage(
                reportType = reportType,
                page = STARTING_PAGE,
                token = userRepository.getToken()
            ).reportInfoList.first().reportId

            check(localLatestId != networkLatestId)

            InitializeAction.LAUNCH_INITIAL_REFRESH
        } catch (e: Exception) {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ReportInfoEntity>
    ): MediatorResult {
        return try {
            val label = "${LABEL_PREFIX}_${reportType.uppercase()}"
            // 获取待加载页
            val loadPage = when (loadType) {
                LoadType.REFRESH -> STARTING_PAGE
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> remotePageDao.getRemotePageNextPage(label)
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
            }
            // 从网络获取报告列表
            val response = networkDataSource.getReportInfoPage(
                reportType = reportType,
                page = loadPage,
                token = userRepository.getToken()
            )
            // 刷新时，删除之前缓存
            if (loadType == LoadType.REFRESH) {
                remotePageDao.deleteRemotePage(label)
                reportInfoDao.deleteAllReportInfo(reportType)
            }
            // 插入新的数据
            remotePageDao.insertRemotePage(
                RemotePageEntity(
                    label = label,
                    nextPage = loadPage + 1
                )
            )
            reportInfoDao.insertReportInfoList(
                response.reportInfoList.map {
                    ReportInfoEntity(
                        reportId = it.reportId,
                        reportType = reportType,
                        reportName = it.reportName,
                        datetime = it.datetime,
                        isPublished = it.isPublished
                    )
                }
            )

            MediatorResult.Success(endOfPaginationReached = !response.hasNextPage)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
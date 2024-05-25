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
import com.zhixue.lite.core.network.model.NetworkReportInfo
import java.text.SimpleDateFormat
import java.util.Locale

private const val LABEL_PREFIX = "REPORT_INFO"
private const val STARTING_PAGE = 1

@OptIn(ExperimentalPagingApi::class)
class ReportInfoRemoteMediator(
    private val type: String,
    private val userRepository: UserRepository,
    private val remotePageDao: RemotePageDao,
    private val reportInfoDao: ReportInfoDao,
    private val networkDataSource: NetworkDataSource
) : RemoteMediator<Int, ReportInfoEntity>() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override suspend fun initialize(): InitializeAction {
        return try {
            val localLatestId = reportInfoDao.getReportInfoEntity(type)?.id
            val networkLatestId = networkDataSource.getReportInfoPage(
                type = type,
                page = STARTING_PAGE,
                token = userRepository.getUserToken()
            ).reportInfoList.first().id

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
            val label = "${LABEL_PREFIX}_${type.uppercase()}"
            // 获取待加载页
            val loadPage = when (loadType) {
                LoadType.REFRESH -> STARTING_PAGE
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> remotePageDao.getRemotePageEntity(label)?.nextPage
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
            }
            // 从网络获取报告列表
            val response = networkDataSource.getReportInfoPage(
                type = type,
                page = loadPage,
                token = userRepository.getUserToken()
            )
            // 刷新时，删除之前缓存
            if (loadType == LoadType.REFRESH) {
                remotePageDao.deleteRemotePageEntity(label)
                reportInfoDao.deleteReportInfoEntities(type)
            }
            // 插入新的数据
            remotePageDao.insertRemotePageEntity(
                RemotePageEntity(label, loadPage + 1)
            )
            reportInfoDao.insertReportInfoEntities(
                response.reportInfoList.mapToReportInfoEntities()
            )

            MediatorResult.Success(endOfPaginationReached = !response.hasNextPage)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun List<NetworkReportInfo>.mapToReportInfoEntities(): List<ReportInfoEntity> =
        map {
            ReportInfoEntity(
                id = it.id,
                type = type,
                name = it.name,
                dateTime = formatter.format(it.dateTime),
                isPublished = it.isPublished
            )
        }
}
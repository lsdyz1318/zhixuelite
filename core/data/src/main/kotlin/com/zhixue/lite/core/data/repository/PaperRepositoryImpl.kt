package com.zhixue.lite.core.data.repository

import com.zhixue.lite.core.database.dao.PaperInfoDao
import com.zhixue.lite.core.database.dao.TrendInfoDao
import com.zhixue.lite.core.database.model.PaperInfoEntity
import com.zhixue.lite.core.database.model.PopulatedPaperInfo
import com.zhixue.lite.core.database.model.TrendInfoEntity
import com.zhixue.lite.core.database.model.asExternalModel
import com.zhixue.lite.core.model.PaperInfo
import com.zhixue.lite.core.model.TrendDirection
import com.zhixue.lite.core.network.NetworkDataSource
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
        runCatching {
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
                    val userScore = networkPaperInfo.userScore
                    val standardScore = networkPaperInfo.standardScore
                    val scoreRate = (userScore / standardScore).toFloat()
                    val classPercentile = networkSubjectDiagnosisList
                        ?.find { it.subjectCode == networkPaperInfo.subjectCode }
                        ?.classPercentile

                    PaperInfoEntity(
                        reportId = reportId,
                        paperId = networkPaperInfo.paperId,
                        subjectName = networkPaperInfo.subjectName,
                        userScore = userScore,
                        standardScore = standardScore,
                        scoreRate = scoreRate,
                        classRank = networkPaperInfo.classRank,
                        classPercentile = classPercentile
                    )
                }
                .run {
                    paperInfoDao.insertPaperInfoList(this)
                }
        }
    }

    override suspend fun syncTrendInfoList(reportId: String, paperId: String) {
        runCatching {
            val networkTrendInfoList = networkDataSource.getTrendInfoList(
                reportId = reportId,
                paperId = paperId,
                token = userRepository.getToken()
            )

            networkTrendInfoList
                .flatMap { networkTrendInfo ->
                    networkTrendInfo.dataList.map { trendData ->
                        TrendInfoEntity(
                            paperId = trendData.paperId,
                            trendCode = networkTrendInfo.tag.code,
                            paperName = trendData.paperName,
                            datetime = trendData.datetime,
                            trendLevel = trendData.level,
                            trendOffset = trendData.improveInfo.offset,
                            trendDirection = when (trendData.improveInfo.tag.code) {
                                "fastUp" -> TrendDirection.FAST_UP
                                "slowUp" -> TrendDirection.SLOW_UP
                                "slowDown" -> TrendDirection.SLOW_DOWN
                                "fastDown" -> TrendDirection.FAST_DOWN
                                else -> TrendDirection.STEADY
                            },
                            studentNumber = trendData.studentNumber
                        )
                    }
                }
                .run {
                    trendInfoDao.insertTrendInfoList(this)
                }
        }
    }
}
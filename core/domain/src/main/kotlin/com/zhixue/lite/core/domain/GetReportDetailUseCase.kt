package com.zhixue.lite.core.domain

import com.zhixue.lite.core.data.repository.PaperRepository
import com.zhixue.lite.core.model.ReportDetail
import java.math.BigDecimal
import javax.inject.Inject
import kotlin.math.roundToInt

class GetReportDetailUseCase @Inject constructor(
    private val paperRepository: PaperRepository
) {
    suspend operator fun invoke(reportId: String): ReportDetail {
        val paperInfoList = paperRepository.getPaperInfoList(reportId)

        check(paperInfoList.isNotEmpty())

        var totalUserScore = BigDecimal.ZERO
        var totalStandardScore = BigDecimal.ZERO

        val overviewInfoList = mutableListOf<ReportDetail.OverviewInfo>()
        val trendInfoList = mutableListOf<ReportDetail.TrendInfo>()

        for (paperInfo in paperInfoList) {
            val paperId = paperInfo.paperId
            val userScore = paperInfo.userScore.toBigDecimal()
            val standardScore = paperInfo.standardScore.toBigDecimal()
            var classRank = paperInfo.classRank
            val classPercentile = paperInfo.classPercentile
            val studentNumber = paperInfo.studentNumber

            if (classRank == null && classPercentile != null && studentNumber != null) {
                classRank = calculateRank(studentNumber, classPercentile)
            }

            if (!paperId.contains("!")) {
                totalUserScore += userScore
                totalStandardScore += standardScore
            }

            overviewInfoList.add(
                ReportDetail.OverviewInfo(
                    paperId = paperId,
                    subjectName = paperInfo.subjectName,
                    userScore = transformPlainString(userScore),
                    standardScore = transformPlainString(standardScore),
                    scoreRate = paperInfo.scoreRate,
                    trendLevel = paperInfo.trendLevel.orEmpty()
                )
            )

            trendInfoList.add(
                ReportDetail.TrendInfo(
                    subjectName = paperInfo.subjectName,
                    classRank = classRank?.toString(),
                    trendDirection = paperInfo.trendDirection,
                )
            )
        }

        return ReportDetail(
            totalInfo = ReportDetail.TotalInfo(
                userScore = transformPlainString(totalUserScore),
                standardScore = transformPlainString(totalStandardScore),
                scoreRate = totalUserScore.toFloat() / totalStandardScore.toFloat()
            ),
            overviewInfoList = overviewInfoList,
            trendInfoList = trendInfoList
        )
    }
}

private fun calculateRank(studentNumber: Int, classPercentile: Double): Int {
    return (studentNumber - (studentNumber - 1) * (100 - classPercentile) / 100).roundToInt()
}

private fun transformPlainString(decimal: BigDecimal): String {
    return decimal.stripTrailingZeros().toPlainString()
}
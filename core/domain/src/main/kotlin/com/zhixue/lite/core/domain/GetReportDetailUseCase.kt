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

        var totalUserScore = BigDecimal.ZERO
        var totalStandardScore = BigDecimal.ZERO

        val reportDetailPaperInfoList = mutableListOf<ReportDetail.PaperInfo>()

        for (paperInfo in paperInfoList) {
            val paperId = paperInfo.paperId
            val userScore = paperInfo.userScore.toBigDecimal()
            val standardScore = paperInfo.standardScore.toBigDecimal()
            var classRank = paperInfo.classRank
            val classPercentile = paperInfo.classPercentile
            val studentNumber = paperInfo.studentNumber

            if (classRank == null && classPercentile != null && studentNumber != null) {
                classRank =
                    (studentNumber - (studentNumber - 1) * (100 - classPercentile) / 100).roundToInt()
            }

            if (!paperId.contains("!")) {
                totalUserScore += userScore
                totalStandardScore += standardScore
            }

            reportDetailPaperInfoList.add(
                ReportDetail.PaperInfo(
                    paperId = paperId,
                    subjectName = paperInfo.subjectName,
                    userScore = transformPlainString(userScore),
                    standardScore = transformPlainString(standardScore),
                    scoreRate = paperInfo.scoreRate,
                    classRank = classRank.toString(),
                    trendLevel = paperInfo.trendLevel.orEmpty(),
                    trendDirection = paperInfo.trendDirection
                )
            )
        }

        return ReportDetail(
            totalInfo = ReportDetail.TotalInfo(
                userScore = transformPlainString(totalUserScore),
                standardScore = transformPlainString(totalStandardScore),
                scoreRate = totalUserScore.toFloat() / totalStandardScore.toFloat()
            ),
            paperInfoList = reportDetailPaperInfoList
        )
    }
}

private fun transformPlainString(decimal: BigDecimal): String {
    return decimal.stripTrailingZeros().toPlainString()
}
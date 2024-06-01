package com.zhixue.lite.feature.report.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zhixue.lite.core.designsystem.component.CircularChart
import com.zhixue.lite.core.designsystem.component.Divider
import com.zhixue.lite.core.designsystem.component.Icon
import com.zhixue.lite.core.designsystem.component.IconButton
import com.zhixue.lite.core.designsystem.component.ProgressBar
import com.zhixue.lite.core.designsystem.component.Text
import com.zhixue.lite.core.designsystem.theme.Theme
import com.zhixue.lite.core.model.ReportDetail
import com.zhixue.lite.core.model.TrendDirection
import com.zhixue.lite.core.common.R as commonR

@Composable
internal fun ReportDetailRoute(
    viewModel: ReportDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onOverviewInfoClick: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.initialize()
    }

    ReportDetailScreen(
        uiState = viewModel.uiState,
        onBackClick = onBackClick,
        onOverviewInfoClick = onOverviewInfoClick
    )
}

@Composable
internal fun ReportDetailScreen(
    uiState: ReportDetailUiState,
    onBackClick: () -> Unit,
    onOverviewInfoClick: (String) -> Unit
) {
    LazyColumn {
        reportDetailHeader(onBackClick = onBackClick)
        reportDetailBody(
            uiState = uiState,
            onOverviewInfoClick = onOverviewInfoClick
        )
    }
}

internal fun LazyListScope.reportDetailHeader(
    onBackClick: () -> Unit
) {
    item {
        Box(modifier = Modifier.padding(16.dp)) {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(commonR.drawable.ic_back),
                    tint = Theme.colorScheme.onBackground
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.padding(horizontal = 32.dp)) {
            Text(
                text = stringResource(R.string.report_detail_title),
                color = Theme.colorScheme.onBackground,
                style = Theme.typography.headline
            )
        }
    }
}

internal fun LazyListScope.reportDetailBody(
    uiState: ReportDetailUiState,
    onOverviewInfoClick: (String) -> Unit
) {
    when (uiState) {
        is ReportDetailUiState.Loading -> {}
        is ReportDetailUiState.Failure -> {}
        is ReportDetailUiState.Success -> {
            item {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .border(1.dp, Theme.colorScheme.outline, Theme.shapes.medium)
                ) {
                    TotalScorePanel(totalInfo = uiState.reportDetail.totalInfo)
                    Divider()
                    OverviewPanel(
                        overviewInfoList = uiState.reportDetail.overviewInfoList,
                        onOverviewInfoClick = onOverviewInfoClick
                    )
                    Divider()
                    TrendPanel(trendInfoList = uiState.reportDetail.trendInfoList)
                }
            }
        }
    }
}

@Composable
internal fun TotalScorePanel(totalInfo: ReportDetail.TotalInfo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 32.dp, end = 24.dp, bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.report_detail_total_score_label),
                color = Theme.colorScheme.onBackgroundVariant,
                style = Theme.typography.titleMedium.copy(fontWeight = FontWeight.Medium)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        Theme.typography.display
                            .copy(color = Theme.colorScheme.onBackground)
                            .toSpanStyle()
                    ) {
                        append(totalInfo.userScore)
                    }
                    withStyle(
                        Theme.typography.titleMedium
                            .copy(color = Theme.colorScheme.onBackground)
                            .toSpanStyle()
                    ) {
                        append(" / ")
                        append(totalInfo.standardScore)
                    }
                }
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
        CircularChart(
            value = totalInfo.scoreRate,
            modifier = Modifier.size(56.dp)
        )
    }
}

@Composable
internal fun OverviewPanel(
    overviewInfoList: List<ReportDetail.OverviewInfo>,
    onOverviewInfoClick: (String) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 24.dp)) {
        Box(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                text = stringResource(R.string.report_detail_overview_label),
                color = Theme.colorScheme.onBackgroundVariant,
                style = Theme.typography.titleMedium.copy(fontWeight = FontWeight.Medium)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            overviewInfoList.forEach { overviewInfo ->
                OverviewItem(
                    overviewInfo = overviewInfo,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clip(Theme.shapes.small)
                        .clickable { onOverviewInfoClick(overviewInfo.paperId) }
                )
            }
        }
    }
}

@Composable
internal fun OverviewItem(
    overviewInfo: ReportDetail.OverviewInfo,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            Theme.typography.bodyMedium
                                .copy(color = Theme.colorScheme.onBackground)
                                .toSpanStyle()
                        ) {
                            append(overviewInfo.subjectName)
                        }
                        withStyle(
                            Theme.typography.bodySmall
                                .copy(color = Theme.colorScheme.onBackground)
                                .toSpanStyle()
                        ) {
                            append(" ")
                            append(overviewInfo.trendLevel)
                        }
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${overviewInfo.userScore} / ${overviewInfo.standardScore}",
                    color = Theme.colorScheme.onBackgroundVariant,
                    style = Theme.typography.bodySmall.copy(fontWeight = FontWeight.Light)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            ProgressBar(
                value = overviewInfo.scoreRate,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
            )
        }
    }
}

@Composable
internal fun TrendPanel(trendInfoList: List<ReportDetail.TrendInfo>) {
    Column(modifier = Modifier.padding(vertical = 24.dp)) {
        Box(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                text = stringResource(R.string.report_detail_trend_label),
                color = Theme.colorScheme.onBackgroundVariant,
                style = Theme.typography.titleMedium.copy(fontWeight = FontWeight.Medium)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            trendInfoList.forEach { trendInfo ->
                TrendItem(trendInfo = trendInfo)
            }
        }
    }
}

@Composable
internal fun TrendItem(
    trendInfo: ReportDetail.TrendInfo,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = trendInfo.subjectName,
                    color = Theme.colorScheme.onBackground,
                    style = Theme.typography.bodyMedium
                )
                trendInfo.classRank?.let { classRank ->
                    Text(
                        text = "班级排名：$classRank",
                        color = Theme.colorScheme.onBackgroundVariant,
                        style = Theme.typography.bodySmall.copy(fontWeight = FontWeight.Light)
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            when (trendInfo.trendDirection) {
                TrendDirection.FAST_UP -> TrendDirectionBox(
                    iconId = commonR.drawable.ic_trending_up,
                    textId = R.string.report_detail_trending_fast_up,
                    color = Theme.colorScheme.primary
                )

                TrendDirection.SLOW_UP -> TrendDirectionBox(
                    iconId = commonR.drawable.ic_trending_up,
                    textId = R.string.report_detail_trending_slow_up,
                    color = Theme.colorScheme.primary.copy(alpha = 0.75f)
                )

                TrendDirection.STEADY -> TrendDirectionBox(
                    iconId = commonR.drawable.ic_trending_flat,
                    textId = R.string.report_detail_trending_steady,
                    color = Theme.colorScheme.onBackgroundVariant
                )

                TrendDirection.SLOW_DOWN -> TrendDirectionBox(
                    iconId = commonR.drawable.ic_trending_down,
                    textId = R.string.report_detail_trending_slow_down,
                    color = Theme.colorScheme.error.copy(alpha = 0.75f)
                )

                TrendDirection.FAST_DOWN -> TrendDirectionBox(
                    iconId = commonR.drawable.ic_trending_down,
                    textId = R.string.report_detail_trending_fast_down,
                    color = Theme.colorScheme.error
                )

                else -> TrendDirectionBox(
                    iconId = commonR.drawable.ic_trending_flat,
                    textId = R.string.report_detail_trending_none,
                    color = Theme.colorScheme.onBackgroundVariant
                )
            }
        }
    }
}

@Composable
internal fun TrendDirectionBox(
    iconId: Int,
    textId: Int,
    color: Color
) {
    Row {
        Icon(
            painter = painterResource(iconId),
            modifier = Modifier.size(16.dp),
            tint = color
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(textId),
            color = color,
            style = Theme.typography.bodySmall
        )
    }
}
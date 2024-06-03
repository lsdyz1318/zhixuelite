package com.zhixue.lite.feature.report

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
internal fun ReportRoute(
    viewModel: ReportViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onOverviewInfoClick: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.initialize()
    }

    ReportScreen(
        uiState = viewModel.uiState,
        onBackClick = onBackClick,
        onOverviewInfoClick = onOverviewInfoClick
    )
}

@Composable
internal fun ReportScreen(
    uiState: ReportUiState,
    onBackClick: () -> Unit,
    onOverviewInfoClick: (String) -> Unit
) {
    LazyColumn {
        reportHeader(onBackClick = onBackClick)
        reportBody(
            uiState = uiState,
            onOverviewInfoClick = onOverviewInfoClick
        )
    }
}

internal fun LazyListScope.reportHeader(
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
                text = stringResource(R.string.report_title),
                color = Theme.colorScheme.onBackground,
                style = Theme.typography.headline
            )
        }
    }
}

internal fun LazyListScope.reportBody(
    uiState: ReportUiState,
    onOverviewInfoClick: (String) -> Unit
) {
    when (uiState) {
        is ReportUiState.Loading -> {}
        is ReportUiState.Failure -> {}
        is ReportUiState.Success -> {
            item {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .border(1.dp, Theme.colorScheme.outline, Theme.shapes.medium)
                ) {
                    TotalScorePanel(
                        totalInfo = uiState.reportDetail.totalInfo,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, top = 32.dp, end = 24.dp, bottom = 24.dp)
                    )
                    Divider()
                    OverviewPanel(
                        overviewInfoList = uiState.reportDetail.overviewInfoList,
                        onOverviewInfoClick = onOverviewInfoClick,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
internal fun TotalScorePanel(
    totalInfo: ReportDetail.TotalInfo,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.report_total_score_label),
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
    onOverviewInfoClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                text = stringResource(R.string.report_overview_label),
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
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
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
                            append(overviewInfo.level)
                        }
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                when (overviewInfo.direction) {
                    TrendDirection.UP -> TrendingIcon(
                        iconId = commonR.drawable.ic_trending_up,
                        tint = Theme.colorScheme.primary
                    )

                    TrendDirection.DOWN -> TrendingIcon(
                        iconId = commonR.drawable.ic_trending_down,
                        tint = Theme.colorScheme.error
                    )

                    else -> TrendingIcon(
                        iconId = commonR.drawable.ic_trending_flat,
                        tint = Theme.colorScheme.onBackgroundVariant
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${overviewInfo.userScore} / ${overviewInfo.standardScore}",
                    color = Theme.colorScheme.onBackgroundVariant,
                    style = Theme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "班级排名：${overviewInfo.classRank}",
                    color = Theme.colorScheme.onBackgroundVariant,
                    style = Theme.typography.bodySmall.copy(fontWeight = FontWeight.Light)
                )
                Spacer(modifier = Modifier.weight(1f))
                ProgressBar(
                    value = overviewInfo.scoreRate,
                    modifier = Modifier
                        .width(48.dp)
                        .height(6.dp)
                )
            }
        }
    }
}

@Composable
internal fun TrendingIcon(iconId: Int, tint: Color) {
    Icon(
        painter = painterResource(iconId),
        modifier = Modifier.size(12.dp),
        tint = tint
    )
}
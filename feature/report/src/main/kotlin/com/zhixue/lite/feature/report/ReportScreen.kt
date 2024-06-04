package com.zhixue.lite.feature.report

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.zhixue.lite.core.designsystem.component.Text
import com.zhixue.lite.core.designsystem.theme.Theme
import com.zhixue.lite.core.model.FormatPaperInfo
import com.zhixue.lite.core.model.ReportDetail
import com.zhixue.lite.core.ui.PaperInfoItem
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
        onPaperClick = onOverviewInfoClick
    )
}

@Composable
internal fun ReportScreen(
    uiState: ReportUiState,
    onBackClick: () -> Unit,
    onPaperClick: (String) -> Unit
) {
    LazyColumn {
        reportHeader(onBackClick = onBackClick)
        reportBody(
            uiState = uiState,
            onPaperClick = onPaperClick
        )
    }
}

internal fun LazyListScope.reportHeader(onBackClick: () -> Unit) {
    item {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                painter = painterResource(commonR.drawable.ic_back),
                tint = Theme.colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.report_title),
            color = Theme.colorScheme.onBackground,
            style = Theme.typography.headline,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}

internal fun LazyListScope.reportBody(
    uiState: ReportUiState,
    onPaperClick: (String) -> Unit
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
                    TotalScorePanel(totalInfo = uiState.reportDetail.totalInfo)
                    Divider()
                    OverviewPanel(
                        overview = uiState.reportDetail.overview,
                        onPaperClick = onPaperClick
                    )
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
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.report_total_score_label),
                color = Theme.colorScheme.onBackgroundVariant,
                style = Theme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = buildAnnotatedString {
                    append(totalInfo.userScore)
                    withStyle(Theme.typography.bodyLarge.toSpanStyle()) {
                        append(" / ")
                        append(totalInfo.standardScore)
                    }
                },
                color = Theme.colorScheme.onBackground,
                style = Theme.typography.display
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
    overview: List<FormatPaperInfo>,
    onPaperClick: (String) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(
            text = stringResource(R.string.report_overview_label),
            color = Theme.colorScheme.onBackgroundVariant,
            style = Theme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            overview.forEach { paperInfo ->
                PaperInfoItem(
                    paperInfo = paperInfo,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clip(Theme.shapes.small)
                        .clickable { onPaperClick(paperInfo.id) }
                )
            }
        }
    }
}
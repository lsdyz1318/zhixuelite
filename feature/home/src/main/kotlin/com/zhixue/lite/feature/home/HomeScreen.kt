package com.zhixue.lite.feature.home

import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.zhixue.lite.core.designsystem.component.Divider
import com.zhixue.lite.core.designsystem.component.Icon
import com.zhixue.lite.core.designsystem.component.Text
import com.zhixue.lite.core.designsystem.component.TextButton
import com.zhixue.lite.core.designsystem.modifier.placeholder
import com.zhixue.lite.core.designsystem.theme.Theme
import com.zhixue.lite.core.model.ReportInfo
import com.zhixue.lite.core.common.R as commonR

@Composable
internal fun HomeRoute(
    onReportInfoClick: (String) -> Unit,
    homeState: HomeState = rememberHomeState(),
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    HomeScreen(
        homeState = homeState,
        examReportInfoList = viewModel.examReportInfoList.collectAsLazyPagingItems(),
        homeworkReportInfoList = viewModel.homeworkReportInfoList.collectAsLazyPagingItems(),
        onReportInfoClick = { isPublished, reportId ->
            if (isPublished) {
                onReportInfoClick(reportId)
            } else {
                Toast.makeText(context, R.string.home_not_supported, Toast.LENGTH_SHORT).show()
            }
        }
    )
}

@Composable
private fun HomeScreen(
    homeState: HomeState,
    examReportInfoList: LazyPagingItems<ReportInfo>,
    homeworkReportInfoList: LazyPagingItems<ReportInfo>,
    onReportInfoClick: (Boolean, String) -> Unit
) {
    Column {
        HomeTabs(
            pageTitleIds = homeState.pageTitleIds,
            currentPage = homeState.currentPage,
            onTabClick = homeState::scrollToPage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 12.dp)
        )
        Divider()
        HorizontalPager(homeState.pagerState) { index ->
            HomeReportInfoPage(
                reportInfoList = when (homeState.pages[index]) {
                    HomePage.EXAM -> examReportInfoList
                    HomePage.HOMEWORK -> homeworkReportInfoList
                },
                onReportInfoClick = onReportInfoClick,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun HomeTabs(
    pageTitleIds: List<Int>,
    currentPage: Int,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
    ) {
        pageTitleIds.forEachIndexed { index, pageTitleId ->
            TextButton(onClick = { onTabClick(index) }) {
                Text(
                    text = stringResource(pageTitleId),
                    color = if (index == currentPage) Theme.colorScheme.primary else Theme.colorScheme.onBackgroundVariant,
                    style = Theme.typography.titleSmall
                )
            }
        }
    }
}

@Composable
private fun HomeReportInfoPage(
    reportInfoList: LazyPagingItems<ReportInfo>,
    onReportInfoClick: (Boolean, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Crossfade(
        label = "HomeReportInfoPage",
        targetState = reportInfoList.loadState.refresh is LoadState.Loading,
        animationSpec = tween(800)
    ) { isLoading ->
        LazyColumn(
            modifier = modifier,
            userScrollEnabled = !isLoading
        ) {
            if (isLoading) {
                placeholderBody()
            } else {
                reportInfoBody(reportInfoList, onReportInfoClick)
            }
        }
    }
}

private fun LazyListScope.placeholderBody() {
    items(20) {
        ReportInfoItemPlaceHolder(
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
        )
        Spacer(modifier = Modifier.height(1.dp))
    }
}

private fun LazyListScope.reportInfoBody(
    reportInfoList: LazyPagingItems<ReportInfo>,
    onReportInfoClick: (Boolean, String) -> Unit
) {
    items(
        count = reportInfoList.itemCount,
        key = reportInfoList.itemKey()
    ) { index ->
        val reportInfo = reportInfoList[index]
        if (reportInfo != null) {
            ReportInfoItem(
                name = reportInfo.name,
                dateTime = reportInfo.dateTime,
                modifier = Modifier
                    .clickable { onReportInfoClick(reportInfo.isPublished, reportInfo.id) }
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            )
            Divider()
        } else {
            ReportInfoItemPlaceHolder(
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
            )
            Spacer(modifier = Modifier.height(1.dp))
        }
    }
}

@Composable
private fun ReportInfoItem(
    name: String,
    dateTime: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = name,
                color = Theme.colorScheme.onBackground,
                style = Theme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = dateTime,
                color = Theme.colorScheme.onBackgroundVariant,
                style = Theme.typography.bodySmall.copy(fontWeight = FontWeight.Light)
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
        Icon(
            painter = painterResource(commonR.drawable.ic_next),
            tint = Theme.colorScheme.onBackgroundVariant
        )
    }
}

@Composable
private fun ReportInfoItemPlaceHolder(modifier: Modifier = Modifier) {
    // 占位符Modifier
    val placeholderModifier = Modifier.placeholder(
        visible = true,
        color = Theme.colorScheme.container,
        highlightColor = Theme.colorScheme.background,
        shape = Theme.shapes.medium
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "",
                color = Theme.colorScheme.onBackground,
                style = Theme.typography.bodyMedium,
                modifier = Modifier
                    .width(168.dp)
                    .then(placeholderModifier)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "",
                color = Theme.colorScheme.onBackgroundVariant,
                style = Theme.typography.bodySmall.copy(fontWeight = FontWeight.Light),
                modifier = Modifier
                    .width(64.dp)
                    .then(placeholderModifier)
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
        Box(
            modifier = Modifier
                .size(24.dp)
                .then(placeholderModifier)
        )
    }
}
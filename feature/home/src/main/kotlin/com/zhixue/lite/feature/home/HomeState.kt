package com.zhixue.lite.feature.home

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun rememberHomeState(
    pages: List<HomePage> = remember { HomePage.entries },
    pagerState: PagerState = rememberPagerState { pages.size },
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): HomeState {
    return remember(
        pages,
        pagerState,
        coroutineScope
    ) {
        HomeState(
            pages = pages,
            pagerState = pagerState,
            coroutineScope = coroutineScope
        )
    }
}

@Stable
internal class HomeState(
    val pages: List<HomePage>,
    val pagerState: PagerState,
    private val coroutineScope: CoroutineScope
) {
    val currentPage: Int
        get() = pagerState.currentPage

    val pageTitleIds = pages.map { it.titleId }

    fun scrollToPage(index: Int) {
        coroutineScope.launch {
            pagerState.animateScrollToPage(index)
        }
    }
}
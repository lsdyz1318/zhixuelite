package com.zhixue.lite.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhixue.lite.core.designsystem.theme.Theme
import com.zhixue.lite.navigation.ZhixueLiteNavHost

@Composable
fun ZhixueLiteApp(appState: ZhixueLiteAppState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colorScheme.background)
            .systemBarsPadding()
    ) {
        ZhixueLiteNavHost(appState = appState)
    }
}
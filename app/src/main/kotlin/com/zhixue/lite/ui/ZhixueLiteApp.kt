package com.zhixue.lite.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.zhixue.lite.core.designsystem.theme.Theme
import com.zhixue.lite.navigation.AuthGraph
import com.zhixue.lite.navigation.MainGraph
import com.zhixue.lite.navigation.ZhixueLiteNavHost

@Composable
fun ZhixueLiteApp(isLoggedIn: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(Theme.colorScheme.background)
    ) {
        ZhixueLiteNavHost(
            navController = rememberNavController(),
            startDestination = if (isLoggedIn) MainGraph else AuthGraph
        )
    }
}
package com.zhixue.lite.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zhixue.lite.feature.home.HomeRoute
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

fun NavGraphBuilder.homeRoute() {
    composable<HomeRoute> {
        HomeRoute()
    }
}
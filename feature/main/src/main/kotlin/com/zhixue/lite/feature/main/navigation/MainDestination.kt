package com.zhixue.lite.feature.main.navigation

import com.zhixue.lite.feature.home.navigation.HomeRoute
import com.zhixue.lite.feature.profile.navigation.ProfileRoute
import com.zhixue.lite.core.common.R as commonR

internal enum class MainDestination(
    val route: Any,
    val iconResId: Int
) {
    HOME(
        route = HomeRoute,
        iconResId = commonR.drawable.ic_home
    ),
    PROFILE(
        route = ProfileRoute,
        iconResId = commonR.drawable.ic_profile
    )
}
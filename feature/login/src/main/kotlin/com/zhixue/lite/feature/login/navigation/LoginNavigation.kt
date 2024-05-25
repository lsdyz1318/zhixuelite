package com.zhixue.lite.feature.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zhixue.lite.feature.login.LoginRoute
import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

fun NavGraphBuilder.loginRoute(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgetPasswordClick: () -> Unit
) {
    composable<LoginRoute> {
        LoginRoute(
            onLoginSuccess = onLoginSuccess,
            onRegisterClick = onRegisterClick,
            onForgetPasswordClick = onForgetPasswordClick
        )
    }
}
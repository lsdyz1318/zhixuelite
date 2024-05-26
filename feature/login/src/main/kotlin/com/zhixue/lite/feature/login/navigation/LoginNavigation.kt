package com.zhixue.lite.feature.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zhixue.lite.feature.login.LoginRoute
import kotlinx.serialization.Serializable

@Serializable
object Login

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    navigate(Login, navOptions)
}

fun NavGraphBuilder.loginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgetPasswordClick: () -> Unit
) {
    composable<Login> {
        LoginRoute(
            onLoginSuccess = onLoginSuccess,
            onRegisterClick = onRegisterClick,
            onForgetPasswordClick = onForgetPasswordClick
        )
    }
}
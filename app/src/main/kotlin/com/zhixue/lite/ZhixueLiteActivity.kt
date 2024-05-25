package com.zhixue.lite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.zhixue.lite.core.designsystem.theme.ZhixueLiteTheme
import com.zhixue.lite.ui.ZhixueLiteApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ZhixueLiteActivity : ComponentActivity() {

    private val viewModel: ZhixueLiteActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        // 设置透明系统栏
        enableEdgeToEdge()
        // 初始化ViewModel
        viewModel.initialize()
        // 获取登录状态成功后移除闪屏页
        splashScreen.setKeepOnScreenCondition { viewModel.loginState == LoginState.Loading }

        setContent {
            ZhixueLiteTheme {
                if (viewModel.loginState != LoginState.Loading) {
                    ZhixueLiteApp(isLoggedIn = viewModel.loginState == LoginState.LoggedIn)
                }
            }
        }
    }
}
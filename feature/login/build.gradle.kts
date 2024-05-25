plugins {
    alias(libs.plugins.zhixuelite.android.feature)
}

android {
    namespace = "com.zhixue.lite.feature.login"
}

dependencies {
    implementation(projects.core.domain)
    implementation(files("libs/captcha.aar"))
}
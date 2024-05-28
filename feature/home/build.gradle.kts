plugins {
    alias(libs.plugins.zhixuelite.android.feature)
}

android {
    namespace = "com.zhixue.lite.feature.home"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)
    implementation(libs.androidx.paging.compose)
}
plugins {
    alias(libs.plugins.zhixuelite.android.feature)
}

android {
    namespace = "com.zhixue.lite.feature.report.detail"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)
}
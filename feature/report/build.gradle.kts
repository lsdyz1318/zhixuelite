plugins {
    alias(libs.plugins.zhixuelite.android.feature)
}

android {
    namespace = "com.zhixue.lite.feature.report"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)
}
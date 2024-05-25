plugins {
    alias(libs.plugins.zhixuelite.android.feature)
}

android {
    namespace = "com.zhixue.lite.feature.main"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.feature.home)
    implementation(projects.feature.profile)
}
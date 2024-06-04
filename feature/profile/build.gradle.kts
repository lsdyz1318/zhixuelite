plugins {
    alias(libs.plugins.zhixuelite.android.feature)
}

android {
    namespace = "com.zhixue.lite.feature.profile"
}

dependencies {
    implementation(projects.core.common)
}
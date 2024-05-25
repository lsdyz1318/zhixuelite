plugins {
    alias(libs.plugins.zhixuelite.android.library)
    alias(libs.plugins.zhixuelite.android.hilt)
}

android {
    namespace = "com.zhixue.lite.core.domain"
}

dependencies {
    api(projects.core.model)
    implementation(projects.core.data)
    implementation(libs.androidx.paging.runtime)
}
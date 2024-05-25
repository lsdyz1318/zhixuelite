plugins {
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.zhixuelite.android.library)
    alias(libs.plugins.zhixuelite.android.hilt)
}

android {
    namespace = "com.zhixue.lite.core.datastore"
}

dependencies {
    api(projects.core.model)
    implementation(projects.core.common)
    implementation(libs.androidx.datastore)
    implementation(libs.kotlinx.serialization.json)
}
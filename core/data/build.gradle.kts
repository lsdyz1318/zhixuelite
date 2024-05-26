plugins {
    alias(libs.plugins.zhixuelite.android.library)
    alias(libs.plugins.zhixuelite.android.hilt)
}

android {
    namespace = "com.zhixue.lite.core.data"
}

dependencies {
    api(projects.core.model)
    implementation(projects.core.database)
    implementation(projects.core.datastore)
    implementation(projects.core.network)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.kotlinx.serialization.json)
}
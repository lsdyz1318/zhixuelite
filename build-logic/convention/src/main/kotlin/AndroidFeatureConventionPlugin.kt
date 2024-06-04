import com.zhixue.lite.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugins.kotlinx.serialization.get().pluginId)
                apply(libs.plugins.zhixuelite.android.library.get().pluginId)
                apply(libs.plugins.zhixuelite.android.compose.get().pluginId)
                apply(libs.plugins.zhixuelite.android.hilt.get().pluginId)
            }

            dependencies {
                add("implementation", project(":core:data"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:ui"))
                add("implementation", libs.androidx.hilt.navigation.compose)
                add("implementation", libs.androidx.navigation.compose)
                add("implementation", libs.kotlinx.serialization.json)
            }
        }
    }
}
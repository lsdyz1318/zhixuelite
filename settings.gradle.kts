pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "zhixuelite"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:common")
include(":core:data")
include(":core:database")
include(":core:datastore")
include(":core:designsystem")
include(":core:domain")
include(":core:model")
include(":core:network")
include(":feature:home")
include(":feature:login")
include(":feature:main")
include(":feature:profile")
include(":feature:report:detail")
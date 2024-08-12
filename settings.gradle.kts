pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

rootProject.name = "JetTaskBoard"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include (":app")
include (":feature:dashboard")
include (":feature:search")
include (":feature:taskboard")
include (":feature:card")
include (":feature:settings")
include (":core:data")
include (":core:domain")
include (":core:common")
include (":core:designsystem")
include (":core:ui")
include (":core:testing")
include (":core:navigation")

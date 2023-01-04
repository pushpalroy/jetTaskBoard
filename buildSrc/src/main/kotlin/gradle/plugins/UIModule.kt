package gradle.plugins
import com.android.build.gradle.LibraryExtension
import com.jetmovies.deps.DependenciesCatalogue
import com.jetmovies.deps.ProjectProperties
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class UIModule : BasePlugin() {

    override fun apply(target: Project) {
        applyPlugins(target)
        super.apply(target)

        applyDependencies(target)

        target.extensions.getByType<LibraryExtension>().apply {
            configureAndroidLibraryBlock(target)
        }
    }
}

private fun applyPlugins(target: Project) {
    with(target) {
        with(plugins) {
            apply(BuildPlugins.JETBRAINS_KOTLIN_ANDROID)
            apply(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
//            apply(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
//            apply(BuildPlugins.KOTLINX_SERIALIZATION)
            apply(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
        }
    }
}

private fun applyDependencies(target: Project) {
    with(target) {
        implementationDependenciesFrom(
            Lib.Android.uiList + Lib.Android.nav +
                Lib.Di.diAndroidList + Lib.Async.asyncAndroidList + Lib.Logger.loggerList
        )
    }
}

fun LibraryExtension.configureAndroidLibraryBlock(project: Project) {
    compileSdkVersion = ProjectProperties.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = (ProjectProperties.MIN_SDK)
        targetSdk = (ProjectProperties.TARGET_SDK)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = Lib.Android.COMPOSE_COMPILER_VERSION
    }
    packagingOptions {
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("LICENSE.txt")
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    project.tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}

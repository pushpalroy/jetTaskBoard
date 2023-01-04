package gradle.plugins

import com.android.build.gradle.AppExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AppModule : BasePlugin() {

    override fun apply(target: Project) {
        applyPlugins(target)
        super.apply(target)

        applyDependencies(target)

        target.extensions.getByType<AppExtension>().apply {
            configureAndroidAppBlock(target)
        }
    }

    private fun AppExtension.configureAndroidAppBlock(target: Project) {
        compileSdkVersion = ProjectProperties.COMPILE_SDK_VERSION

        defaultConfig(target)

        kotlinCompileJVMVersion(target)

        buildTypes()

        buildFeatures()

        packagingOptions()

        compileOptions()
    }

    private fun applyDependencies(target: Project) {
        with(target) {
            implementationProjectsFrom(
                listOf(
                    ":core:data",
                    ":core:navigation",
                    ":core:commonui",
                    ":core:domain",
                    ":features:home",
                    ":features:profile"
                )
            )
            implementationDependenciesFrom(
                Lib.Android.uiList + Lib.Android.nav +
                    Lib.Di.diAndroidList + Lib.Async.asyncAndroidList +
                    Lib.Logger.loggerList + Lib.Crash.crashList
            )
        }
    }

    private fun applyPlugins(target: Project) {
        with(target) {
            with(plugins) {
                apply(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
                apply(BuildPlugins.ANDROID_APPLICATION_PLUGIN)
                apply(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
                apply(BuildPlugins.KOTLIN_KAPT)
            }
        }
    }
}

private fun AppExtension.defaultConfig(target: Project) {
    defaultConfig {
        namespace = ProjectProperties.APPLICATION_ID
        minSdk = ProjectProperties.MIN_SDK
        targetSdk = ProjectProperties.COMPILE_SDK
        testInstrumentationRunner = ("android.support.test.runner.AndroidJUnitRunner")
        vectorDrawables.useSupportLibrary = true

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "${target.projectDir}/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }
}

private fun AppExtension.buildFeatures() {
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = Lib.Android.COMPOSE_COMPILER_VERSION
    }
}

private fun kotlinCompileJVMVersion(target: Project) {
    target.tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}

private fun AppExtension.compileOptions() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

private fun AppExtension.packagingOptions() {
    packagingOptions {
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("LICENSE.txt")
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

private fun AppExtension.buildTypes() {
    buildTypes.apply {
        this.getByName("debug").apply {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

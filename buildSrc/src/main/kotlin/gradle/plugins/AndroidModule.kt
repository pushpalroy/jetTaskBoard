package gradle.plugins

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidModule : BasePlugin() {
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
            apply(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
            apply(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
            apply(BuildPlugins.KOTLIN_KAPT)
            apply(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
        }
    }
}

private fun applyDependencies(target: Project) {
    with(target) {
        implementationDependenciesFrom(
            Lib.Di.diAndroidList + Lib.Async.asyncAndroidList + Lib.Logger.loggerList +
                ktorList
        )
    }
}

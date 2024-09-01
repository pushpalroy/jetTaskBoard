plugins {
    id(libs.plugins.android.application.get().pluginId)
    id("org.jetbrains.kotlin.android")
    id(libs.plugins.compose.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
    id(libs.plugins.baselineprofile.get().pluginId)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.jetapps.jettaskboard"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jetapps.jettaskboard"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isDebuggable = true
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-common.txt",
                "proguard-specific.txt"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}


dependencies {

    // Projects
    implementation(projects.feature.dashboard)
    implementation(projects.feature.card)
    implementation(projects.feature.taskboard)
    implementation(projects.feature.search)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.designsystem)
    implementation(projects.core.navigation)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.compose.material3.adaptive.layout)
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.tracing.ktx)
    implementation(libs.androidx.compose.constraint.layout)
    implementation(libs.androidx.compose.accompanist.insets)
    implementation(libs.androidx.compose.accompanist.insets.ui)
    implementation(libs.androidx.compose.accompanist.flow.layout)

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.window.core)
    implementation(libs.androidx.window)

    // Kotlin
//    implementation(libs.kotlin.stdlib)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

//    implementation(libs.hilt.core)
//    implementation(libs.hilt.compiler)
//    implementation(libs.hilt.ext.compiler)
//    implementation(libs.hilt.ext.work)

    // Tooling
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Coil
    implementation(libs.coil.kt)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Profile Installer
    implementation(libs.androidx.profileinstaller)

    /* Logger */
    implementation(libs.timber)

    /* Async */
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    /* Glance AppWidgets */
    implementation(libs.glance)

    /* Room */
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)

    /* Datastore */
    implementation(libs.androidx.dataStore)
    implementation(libs.androidx.dataStore.core)

    debugImplementation(libs.androidx.compose.ui.testManifest)

//    Test Dependencies
    kspTest(libs.hilt.compiler)
    testImplementation(libs.junit)
//    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.androidx.arch.core)
    testImplementation(libs.mockk)
    androidTestImplementation(kotlin("test"))
    androidTestImplementation(projects.core.testing)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(libs.hilt.android.testing)
}

ktlint {
    android.set(true)
    outputColorName.set("RED")
}

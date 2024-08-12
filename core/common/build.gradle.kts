plugins {
    id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
    id("org.jetbrains.kotlin.android")
    id(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
    id(libs.plugins.compose.get().pluginId)
//    alias(libs.plugins.compose)
}

android {
    namespace = "com.jetapps.jettaskboard.core.common"
    compileSdk = (ProjectProperties.COMPILE_SDK)

    defaultConfig {
        minSdk = (ProjectProperties.MIN_SDK)
        targetSdk = (ProjectProperties.TARGET_SDK)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}


dependencies {
    /* Android Designing and layout */
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.compose.material)

    /* Testing */
    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.core.ktx)
//    testImplementation(libs.androidx.test.ext.junit)
}

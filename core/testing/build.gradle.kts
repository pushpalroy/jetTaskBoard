plugins {
    id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
    id("org.jetbrains.kotlin.android")
    id(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
    alias(libs.plugins.compose)
}

android {
    namespace = "com.jetapps.jettaskboard.core.testing"
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
    implementation(libs.androidx.test.ext)

    /* Testing */
    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.core)
//    testImplementation(libs.androidx.test.ext.junit)
}

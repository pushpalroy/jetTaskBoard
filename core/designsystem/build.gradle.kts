plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.compose)
    id("org.jetbrains.kotlin.plugin.parcelize")
}

android {
    namespace = "com.jetapps.jettaskboard.core.designsystem"
    compileSdk = 35

    defaultConfig {
        minSdk = 28
        targetSdk = 35
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

    implementation(projects.core.domain)

    /* Android Designing and layout */
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.compose.material)

    /* Image Loading */
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)

    /* Testing */
    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.core)
//    testImplementation(libs.androidx.test.ext.junit)
}

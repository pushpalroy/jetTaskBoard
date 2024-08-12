plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    alias(libs.plugins.compose)
}

android {
    namespace = "com.jetapps.jettaskboard.core.data"
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
    implementation(projects.core.common)

    /* Android Designing and layout */
    implementation(libs.compose.material)

    /* Room */
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)

    /* Datastore */
    implementation(libs.androidx.dataStore)
    implementation(libs.androidx.dataStore.core)
    implementation(libs.androidx.datastore.preferences.core.jvm)

    /* Dependency Injection */
    api(libs.hilt.android)
    ksp(libs.hilt.compiler)

    /** Networking **/
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.logging)
    implementation(libs.gson)

    /* Testing */
    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.core)
//    testImplementation(libs.androidx.test.ext.junit)
}

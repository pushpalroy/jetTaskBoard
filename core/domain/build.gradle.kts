plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("com.google.devtools.ksp")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
//    api(libs.kotlin.stdlib)
    api(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.paging.common.ktx)
//    implementation(libs.hilt.core)
    implementation(libs.hilt.core)
}

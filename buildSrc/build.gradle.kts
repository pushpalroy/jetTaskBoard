repositories {
    mavenCentral()
    google()
}

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.android.tools.build:gradle:7.2.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
}



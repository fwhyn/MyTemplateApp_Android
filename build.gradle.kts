// Top-level build file where you can add configuration options common to all sub-projects/modules.
rootProject.run {
    extra["lSdk"] = 24
    extra["mSdk"] = 34

    extra["javaVersion"] = JavaVersion.VERSION_1_8
}

plugins {
    id("com.android.application") version "8.1.1" apply false
    id("com.android.library") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
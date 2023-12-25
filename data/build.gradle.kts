plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    val javaVersion: JavaVersion by rootProject.extra

    namespace = "com.fwhyn.data"
    compileSdk = rootProject.extra["mSdk"] as Int

    defaultConfig {
        minSdk = rootProject.extra["lSdk"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }
}

dependencies {
    // ----------------------------------------------------------------
    // Main


    // ----------------------------------------------------------------
    // Test
    testImplementation(libs.junit)
}
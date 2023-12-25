plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    val moduleName = "com.fwhyn.mytempapp"

    val lSdk: Int by rootProject.extra
    val mSdk: Int by rootProject.extra

    val javaVersion: JavaVersion by rootProject.extra

    namespace = moduleName
    compileSdk = mSdk

    defaultConfig {
        applicationId = moduleName
        minSdk = lSdk
        targetSdk = mSdk
        versionCode = 1000100
        versionName = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // ----------------------------------------------------------------
    // Main
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.fragment.ktx)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.compose)

    // ----------------------------------------------------------------
    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)

    // Compose
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.androidx.compose.androidtest)
    debugImplementation(libs.bundles.androidx.compose.debugtest)
}
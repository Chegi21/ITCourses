plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.bilev.itcourses"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.bilev.itcourses"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation (project(":domain"))
    implementation (project(":data"))

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp3)

    annotationProcessor(libs.room.compiler)
    implementation(libs.room.runtime)

    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)

    implementation(libs.adapter)

    implementation(libs.navigationF)
    implementation(libs.navigationUi)

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
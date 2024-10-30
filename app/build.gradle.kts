import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.dagger.hilt.android)
    alias(shihcheeng.plugins.ksp)
    alias(shihcheeng.plugins.compose.compiler)
}
val file = file("../gradle/version.log.properties")
android {
    namespace = "com.shihcheeng.hacgcompose"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.shihcheeng.hacgcompose"
        minSdk = 29
        targetSdk = 35
        val versionCodeProperty = Properties()
        versionCodeProperty.load(file.inputStream())
        val versionInner = versionCodeProperty.getProperty("version").toInt() + 1
        versionCodeProperty["version"] = versionInner.toString()
        versionCode = versionInner
        versionName = "1.0.1"
        versionCodeProperty.store(file.writer(), null)
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".dev"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.tools.retrofit)
    implementation(libs.tools.hilt)
    implementation(libs.tools.jsoup)
    ksp(libs.tools.hilt.complier)
    implementation(shihcheeng.androidx.nav)
    implementation(shihcheeng.tools.motion)
    implementation(shihcheeng.tools.coil)
    implementation(shihcheeng.tools.coil.compose)
    implementation(platform(shihcheeng.tools.okhttp.bom))
    implementation(shihcheeng.tools.okhttp)
    implementation(shihcheeng.androidx.paging.runtime)
    implementation(shihcheeng.androidx.paging.compose)
    implementation(shihcheeng.tools.hilt.navigation.compose)
    implementation(shihcheeng.androidx.room)
    implementation(shihcheeng.androidx.room.ktx)
    ksp(shihcheeng.androidx.room.complier)
    implementation(everyuse.tools.rating.bar)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
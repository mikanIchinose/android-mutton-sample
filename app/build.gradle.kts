plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinCompose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.secretGradlePlugin)
}

android {
    namespace = "io.github.mikan.sample.mutton"
    compileSdk = 36

    defaultConfig {
        applicationId = "io.github.mikan.sample.mutton"
        minSdk = 24
        targetSdk = 36
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":network"))

    implementation(libs.androidxCoreKtx)
    implementation(libs.androidxLifecycleRuntimeKtx)
    implementation(libs.androidxLifecycleViewModelCompose)
    implementation(libs.androidxActivityCompose)
    implementation(platform(libs.androidxComposeBom))
    implementation(libs.androidxUi)
    implementation(libs.androidxUiGraphics)
    implementation(libs.androidxUiToolingPreview)
    implementation(libs.androidxMaterial3)
    implementation(libs.androidxMaterialIconsCore)
    debugImplementation(libs.androidxUiTooling)
    debugImplementation(libs.androidxUiTestManifest)
    implementation(libs.kotlinxDateTime)
    implementation(libs.composeMarkdown)

    // di
    implementation(libs.hiltAndroid)
    ksp(libs.hiltCompiler)

    // image
    implementation(libs.coilCompose)
    implementation(libs.coilNetworkOkhttp)

    // navigation
    implementation(libs.androidxNavigation3Runtime)
    implementation(libs.androidxNavigation3Ui)
    implementation(libs.androidxMaterial3Navigation3)
    implementation(libs.androidxLifecycleViewmodelNavigation3)
    implementation(libs.hiltNavigationCompose)
    implementation(libs.kotlinxSerializationJson)

    // test
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.kotlinTest)
}

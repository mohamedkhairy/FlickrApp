import dependencies.BuildVersion
import dependencies.AndroidX
import dependencies.Hilt
import dependencies.Kotlinx
import dependencies.Google
import dependencies.Glide
import dependencies.Navigation


plugins {
    id ("com.android.library")
    kotlin("android")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")

}

android {
    namespace = "com.example.presentation"
    compileSdk = BuildVersion.compileSdk

    defaultConfig {
        minSdk = BuildVersion.minSdk
        targetSdk = BuildVersion.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)

    implementation(AndroidX.constraintlayout)
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.lifecycleVmKtx)
    implementation(AndroidX.livedataKtx)
    implementation(AndroidX.fragmentKtx)



    implementation(Hilt.android)
    kapt(Hilt.compiler)

    implementation(Google.material)

    implementation(Navigation.navigation_fragment)
    implementation(Navigation.navigation_ui)

    implementation(Glide.glide)
    kapt(Glide.gildeCompiler)

    implementation(Kotlinx.coroutinesCore)
    implementation(Kotlinx.swiperefreshlayout)

    implementation(project(":domain"))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
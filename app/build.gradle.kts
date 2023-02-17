import dependencies.BuildVersion
import dependencies.AndroidX
import dependencies.Google
import dependencies.Hilt
import dependencies.Kotlinx
import dependencies.Ktor
import dependencies.Serialization
import dependencies.Kotlin
import dependencies.KeyHelper
import dependencies.Glide
import dependencies.Navigation



plugins {
    id("com.android.application")
    kotlin("android")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.5.31"
    id("kotlin-android")
    id ("com.google.devtools.ksp") version "1.6.10-1.0.2"
    id("androidx.navigation.safeargs.kotlin")

}


android {

    namespace = BuildVersion.appId
    compileSdk = BuildVersion.compileSdk
    defaultConfig {
        applicationId  = BuildVersion.appId
        minSdk = BuildVersion.minSdk
        targetSdk = BuildVersion.targetSdk
        versionCode  = BuildVersion.versionCode
        versionName = BuildVersion.versionName
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY", KeyHelper.getValue("API_KEY"))
    }



    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility  = JavaVersion.VERSION_1_8
        targetCompatibility  = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = BuildVersion.jvmTarget
    }

    buildFeatures {
        viewBinding = true
    }


}



dependencies {
    implementation (Kotlin.kotlinStdlib)

    implementation(AndroidX.constraintlayout)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.lifecycleRuntime)
    implementation(AndroidX.lifecycleVmKtx)
    implementation(AndroidX.livedataKtx)
    implementation(AndroidX.fragmentKtx)

    implementation(Google.material)
    implementation(Hilt.android)
    implementation(Hilt.hiltNavigation)
    kapt(Hilt.compiler)

    implementation(Kotlinx.coroutinesCore)

    implementation(Serialization.serialization)


    implementation(Navigation.navigation_fragment)
    implementation(Navigation.navigation_ui)

    implementation(Glide.glide)
    kapt(Glide.gildeCompiler)


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")



}
kapt {
    correctErrorTypes = true
}


import dependencies.BuildVersion
import dependencies.AndroidX
import dependencies.Kotlinx
import dependencies.Ktor
import dependencies.KeyHelper
import dependencies.Room

plugins {
    id("com.android.library")
    kotlin("android")
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.data"
    compileSdk = BuildVersion.compileSdk

    defaultConfig {
        minSdk = BuildVersion.minSdk
        targetSdk = BuildVersion.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "API_KEY", KeyHelper.getValue("API_KEY"))

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
        jvmTarget = BuildVersion.jvmTarget
    }
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)

    implementation(Kotlinx.javaxInject)

    implementation(Ktor.core)
    implementation(Ktor.clientSerialization)
    implementation(Ktor.android)
    implementation(Ktor.ktorJson)
    implementation(Ktor.logging)

    implementation(Room.room)
    implementation(Room.roomKtx)
    kapt(Room.roomCompiler)
    kapt(Room.roomKtx)

    implementation(project(":domain"))


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
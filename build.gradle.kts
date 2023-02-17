plugins {
    id ("com.android.application") version "7.3.1" apply false
    id ("com.android.library") version "7.3.1" apply false
    id ("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id ("com.google.dagger.hilt.android") version "2.41" apply false
    id ("androidx.navigation.safeargs") version "2.5.3" apply false
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}

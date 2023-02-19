package dependencies

object Hilt {
    const val hiltVersion = "2.45"

    val android by lazy { "com.google.dagger:hilt-android:$hiltVersion" }
    val compiler by lazy { "com.google.dagger:hilt-compiler:$hiltVersion" }


}

//object HiltTest {
//    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Hilt.hiltVersion}"
//}
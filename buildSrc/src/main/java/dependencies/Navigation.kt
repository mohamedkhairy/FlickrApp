package dependencies

object Navigation {

    private const val navigation_version = "2.5.3"
    val navigation_fragment by lazy { "androidx.navigation:navigation-fragment-ktx:$navigation_version" }
    val navigation_ui by lazy { "androidx.navigation:navigation-ui-ktx:$navigation_version" }


}
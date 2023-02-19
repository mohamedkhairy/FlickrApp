package dependencies

object Kotlinx {

    private const val coroutinesCoreVersion = "1.6.2"
    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesCoreVersion" }

    private const val javaxInjectVersion = "1"
    val javaxInject by lazy { "javax.inject:javax.inject:$javaxInjectVersion" }

    private const val swiperefreshlayoutVersion = "1.1.0"
    val swiperefreshlayout by lazy { "androidx.swiperefreshlayout:swiperefreshlayout:$swiperefreshlayoutVersion" }
}
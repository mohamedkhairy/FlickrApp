package dependencies

import java.io.File
import java.io.FileInputStream
import java.util.*


object KeyHelper {


private val properties by lazy {
    Properties().apply {
        load(FileInputStream(File("apikey.properties")))
    }
}

    fun getValue(key: String): String{
        return properties.getProperty(key)
    }

}
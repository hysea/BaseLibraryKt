package com.hysea.library

import com.hysea.library.utils.*
import org.junit.Test
import java.io.File

/**
 * create by hysea on 2020/1/19
 */
class FileUnitTest {

    @Test
    fun isExists() {
        var file: File? = null
        println(file.exists())
        assert(!file.exists())

        file = File("D:\\test")
        println(file.exists())
        assert(file.exists())
    }

    @Test
    fun testEnsureDirectoryExists() {
        val file = ensureDirectoryExists(File("D:\\test"))
        println(file.absoluteFile)
    }


    @Test
    fun testFilePath() {
        val filePath = File("D:\\test\\hello_world.txt").absolutePath
        println(filePath.getFileExt())
        println(filePath.getFileNameWithExt())
        println(filePath.getFileName())
    }
}
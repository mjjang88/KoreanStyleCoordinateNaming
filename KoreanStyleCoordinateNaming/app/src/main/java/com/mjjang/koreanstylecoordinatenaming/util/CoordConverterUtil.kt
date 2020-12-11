package com.mjjang.koreanstylecoordinatenaming.util

import android.content.Context
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception

object CoordConverterUtil {
    val wordMap = HashMap<Int, String> (3000)
    val wordMapReverse = HashMap<String, Int> (3000)

    fun importWords(context: Context) {
        val assetManager = context.resources.assets

        try {
            val inputStream = assetManager.open("korean.txt")
            val br = BufferedReader(InputStreamReader(inputStream, "UTF-8"))

            br.useLines {
                for ((index, value) in it.withIndex()) {
                    wordMap[index] = value
                    wordMapReverse[value] = index
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
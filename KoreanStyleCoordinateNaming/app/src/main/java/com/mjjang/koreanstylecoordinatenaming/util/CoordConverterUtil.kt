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

    val MIN_X = 670000
    val MAX_X = 1400000
    val MIN_Y = 1370000
    val MAX_Y = 2100000
    val SEPERATE_FACTOR = 73000

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

    fun getKrCoordName(x: Int, y: Int) : String {
        if (!isCoordValid(x, y)) {
            return "Invalid"
        }

        val sum = ((x - MIN_X) / 10) + (((y - MIN_Y) / 10) * SEPERATE_FACTOR)

        val s1Index = sum / wordMap.size
        val s2Index = (sum % wordMap.size) / wordMap.size
        val s3Index = (sum % wordMap.size) % wordMap.size

        return "${wordMap[s1Index]}${wordMap[s2Index]}${wordMap[s3Index]}"
    }


    fun isCoordValid(x: Int, y: Int): Boolean {
        return x in MIN_X..MAX_X && y in MIN_Y..MAX_Y
    }
}
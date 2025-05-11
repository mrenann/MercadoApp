package com.mrenann.mercadolivre.core.utils

import android.content.Context
import android.util.Log
import java.io.IOException

object JsonUtils {

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            Log.e("JsonUtils", "Error reading JSON file: ${ioException.message}")
            null
        }
    }
}
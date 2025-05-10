package com.mrenann.mercadolivre.core.utils

import android.content.Context
import java.io.IOException

object JsonUtils {

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            null
        }
    }
}
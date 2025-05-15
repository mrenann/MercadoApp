package com.mrenann.mercadolivre.core.utils

import java.text.Normalizer
import java.util.Locale

fun String.forceHttps(): String {
    return if (startsWith("http://")) {
        replaceFirst("http://", "https://")
    } else {
        this
    }
}


fun String.purify(): String {
    return Normalizer.normalize(this.trim().lowercase(Locale.getDefault()), Normalizer.Form.NFD)
        .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
        .replace("ç", "c")
        .replace("ñ", "n")
}
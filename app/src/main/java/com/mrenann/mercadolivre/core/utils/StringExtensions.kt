package com.mrenann.mercadolivre.core.utils

fun String.forceHttps(): String {
    return if (startsWith("http://")) {
        replaceFirst("http://", "https://")
    } else this
}
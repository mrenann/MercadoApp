package com.mrenann.mercadolivre.core.utils

import java.util.Locale

fun String.toSymbol(): String {
    return when (this) {
        "ARS" -> "$"
        "BRL" -> "R$"
        else -> "$"
    }
}

fun Int.formatBalance(
    withDecimals: Boolean = true,
    currency: String = "BRL",
    showCurrency: Boolean = true
): String {
    val symbol = currency.toSymbol()
    val formattedValue = if (withDecimals) {
        String.format(Locale.getDefault(), "%,d", this) + ",00"
    } else {
        String.format(Locale.getDefault(), "%,d", this)
    }

    return if (showCurrency) "$symbol $formattedValue" else formattedValue
}

fun Double.formatBalance(
    withDecimals: Boolean = true,
    currency: String = "BRL",
    showCurrency: Boolean = true
): String {
    val symbol = currency.toSymbol()
    val formattedValue = if (withDecimals) {
        String.format(Locale.getDefault(), "%,.2f", this)
    } else {
        String.format(Locale.getDefault(), "%,.0f", this)
    }

    return if (showCurrency) "$symbol $formattedValue" else formattedValue
}

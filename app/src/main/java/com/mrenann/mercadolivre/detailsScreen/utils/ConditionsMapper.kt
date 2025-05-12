package com.mrenann.mercadolivre.detailsScreen.utils

fun String.toCondition() =
    when (this) {
        "new" -> "Novo"
        "used" -> "Usado"
        "not_specified" -> "Não especificado"
        else -> "Não especificado"
    }

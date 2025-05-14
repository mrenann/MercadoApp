package com.mrenann.mercadolivre.ui.strings

data class Strings(
    val coreStrings: CoreStrings,
    val detailsStrings: DetailsStrings,
    val homeStrings: HomeStrings,
    val searchStrings: SearchStrings
)

data class HomeStrings(
    val cca: String
)

data class CoreStrings(
    val anErrorOccurred: String,
    val errorIconDescription: String,
    val backIconDescription: String,
    val cartIconDescription: String,
    val pinIconDescription: String,
    val nextIconDescription: String,
    val searchIconDescription: String,
    val searchOnMercadoLivre: String,
    val mobileChallenge: String,
)

data class SearchStrings(
    val withouTitle: String,
    val currencyDefault: String,
    val freeShipping: String,
    val searchOnMercadoLivre: String,
    val clearIconDescription: String,
    val backIconDescription: String,
    val backToHome: String,
    val recentSearches: String
)

data class DetailsStrings(
    val cca: String
)
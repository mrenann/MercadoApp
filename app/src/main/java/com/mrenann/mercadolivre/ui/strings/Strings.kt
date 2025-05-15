package com.mrenann.mercadolivre.ui.strings

data class Strings(
    val coreStrings: CoreStrings,
    val detailsStrings: DetailsStrings,
    val searchStrings: SearchStrings
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
    val withoutTitle: String,
    val currencyDefault: String,
    val freeShipping: String,
    val searchOnMercadoLivre: String,
    val clearIconDescription: String,
    val backIconDescription: String,
    val backToHome: String,
    val recentSearches: String,
    val withoutRecentSearches: String,
)

data class DetailsStrings(
    val withoutTitle: String,
    val backIconDescription: String,
    val description: String,
    val withoutDescription: String,
    val productCondition: (condition: String) -> String,
    val currencyDefault: String,
    val discount: (count: Int) -> String,
    val quantityOne: String,
    val moreFifty: String,
    val image: String,
    val productImage: String,
    val currentPageAndImagesSize: (currentPage: Int, imagesSize: Int) -> String,
    val availableQuantity: (quantity: String) -> String,
    val loadDataError: String,
)
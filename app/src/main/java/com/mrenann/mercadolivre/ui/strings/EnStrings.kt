package com.mrenann.mercadolivre.ui.strings

import cafe.adriel.lyricist.LyricistStrings

@LyricistStrings(languageTag = Locales.EN)
internal val EnStrings = Strings(
    coreStrings = CoreStrings(
        anErrorOccurred = "An error occurred",
        backIconDescription = "Back",
        errorIconDescription = "Error",
        cartIconDescription = "Cart",
        pinIconDescription = "Pin",
        nextIconDescription = "Next",
        searchIconDescription = "Search",
        searchOnMercadoLivre = "Search on Mercado Livre",
        mobileChallenge = "Mobile Challenge"
    ),
    detailsStrings = DetailsStrings(
        discount = { count -> " $count% OFF" },
        description = "Description",
        withoutDescription = "No description available",
        currencyDefault = "BRL",
        quantityOne = "Quantity: 1",
        availableQuantity = { quantity -> "($quantity Available)" },
        moreFifty = "+50",
        image = "Image",
        currentPageAndImagesSize = { currentPage, imagesSize -> "$currentPage / $imagesSize" },
        productImage = "Product Image",
        backIconDescription = "Back",
        loadDataError = "Error loading data",
        withoutTitle = "Untitled",
        productCondition = { condition -> "$condition  | +10k sold" }
    ),
    homeStrings = HomeStrings(
        cca = ""
    ),
    searchStrings = SearchStrings(
        withoutTitle = "Untitled",
        currencyDefault = "BRL",
        freeShipping = "Free Shipping",
        searchOnMercadoLivre = "Search on Mercado Livre",
        clearIconDescription = "Clear Search",
        backIconDescription = "Back",
        backToHome = "Back to Home",
        recentSearches = "Recent Searches"
    )
)
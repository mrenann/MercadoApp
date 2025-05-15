package com.mrenann.mercadolivre.ui.strings

import cafe.adriel.lyricist.LyricistStrings

@LyricistStrings(languageTag = Locales.ES)
internal val EsStrings = Strings(
    coreStrings = CoreStrings(
        anErrorOccurred = "Ocurrió un error",
        backIconDescription = "Volver",
        cartIconDescription = "Carrito",
        pinIconDescription = "Fijar",
        nextIconDescription = "Siguiente",
        searchIconDescription = "Buscar",
        searchOnMercadoLivre = "Buscar en Mercado Libre",
        mobileChallenge = "Desafío Mobile",
        errorIconDescription = "Erro"
    ),
    detailsStrings = DetailsStrings(
        discount = { count -> " $count% OFF" },
        description = "Descripción",
        withoutDescription = "Sin descripción disponible",
        currencyDefault = "ARS",
        quantityOne = "Cantidad: 1",
        availableQuantity = { quantity -> "($quantity Disponibles)" },
        moreFifty = "+50",
        image = "Imagen",
        currentPageAndImagesSize = { currentPage, imagesSize -> "$currentPage / $imagesSize" },
        productImage = "Imagen del producto",
        backIconDescription = "Volver",
        loadDataError = "Error al cargar los datos",
        withoutTitle = "Sin título",
        productCondition = { condition -> "$condition  | +10mil vendidos" }
    ),
    searchStrings = SearchStrings(
        withoutTitle = "Sin título",
        currencyDefault = "ARS",
        freeShipping = "Envío gratis",
        searchOnMercadoLivre = "Buscar en Mercado Libre",
        clearIconDescription = "Limpiar búsqueda",
        backIconDescription = "Volver",
        backToHome = "Volver a Inicio",
        recentSearches = "Datos mockados: arroz, cafe, iphone, camisa, zapatillas",
        withoutRecentSearches = "Sin búsquedas recientes",
    )
)

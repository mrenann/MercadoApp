package com.mrenann.mercadolivre.ui.strings

import cafe.adriel.lyricist.LyricistStrings

@LyricistStrings(languageTag = Locales.PT, default = true)
internal val PtStrings = Strings(
    coreStrings = CoreStrings(
        anErrorOccurred = "Ocorreu um erro",
        backIconDescription = "Voltar",
        cartIconDescription = "Carrinho",
        pinIconDescription = "Pin",
        nextIconDescription = "Próximo",
        searchIconDescription = "Pesquisar",
        searchOnMercadoLivre = "Procurar no Mercado Livre",
        mobileChallenge = "Desafio Mobile",
        errorIconDescription = "Erro"
    ),
    detailsStrings = DetailsStrings(
        discount = { count -> " $count% OFF" },
        description = "Descrição",
        withoutDescription = "Sem descrição disponível",
        currencyDefault = "BRL",
        quantityOne = "Quantidade: 1",
        availableQuantity = { quantity -> "($quantity Disponíveis)" },
        moreFifty = "+50",
        image = "Imagem",
        currentPageAndImagesSize = { currentPage, imagesSize -> "$currentPage / $imagesSize" },
        productImage = "Imagem do Produto",
        backIconDescription = "Voltar",
        loadDataError = "Erro ao carregar os dados",
        withoutTitle = "Sem Título",
        productCondition = { condition -> "$condition  | +10mil vendidos" }
    ),
    homeStrings = HomeStrings(
        cca = ""
    ),
    searchStrings = SearchStrings(
        withoutTitle = "Sem Título",
        currencyDefault = "BRL",
        freeShipping = "Frete Grátis",
        searchOnMercadoLivre = "Buscar no Mercado Livre",
        clearIconDescription = "Limpar busca",
        backIconDescription = "Voltar",
        backToHome = "Voltar para o Inicio",
        recentSearches = "Ultimas Buscas"
    )
)
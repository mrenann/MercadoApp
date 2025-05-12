package com.mrenann.mercadolivre.core.data.remote.mappers

import com.mrenann.mercadolivre.core.data.remote.model.search.Result
import com.mrenann.mercadolivre.core.domain.model.SearchResult
import com.mrenann.mercadolivre.core.utils.forceHttps


fun Result.toDomain(): SearchResult {
    return SearchResult(
        id = id,
        thumbnail = thumbnail?.forceHttps(),
        title = title,
        originalPrice = originalPrice,
        price = price,
        freeShipping = shipping?.freeShipping,
        currencyId = currencyId
    )
}

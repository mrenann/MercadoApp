package com.mrenann.mercadolivre.searchScreen.presentation.state

import com.mrenann.mercadolivre.core.data.remote.model.search.Result

data class SearchState(
    val query: String = "",
    val items: List<Result> = emptyList()
)
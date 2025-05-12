package com.mrenann.mercadolivre.searchScreen.presentation.state

import com.mrenann.mercadolivre.core.domain.model.SearchResult

data class SearchState(
    val query: String = "",
    val items: List<SearchResult> = emptyList()
)
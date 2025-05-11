package com.mrenann.mercadolivre.searchScreen.presentation.state

import com.mrenann.mercadolivre.core.data.remote.model.Result

data class SearchState(
    val query: String = "",
    val items: List<Result> = emptyList()
)
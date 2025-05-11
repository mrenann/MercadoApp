package com.mrenann.mercadolivre.searchScreen.presentation.screenModel

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.searchScreen.domain.repository.SearchRepository
import com.mrenann.mercadolivre.searchScreen.presentation.state.SearchState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchScreenModel(
    private val searchRepository: SearchRepository
) : StateScreenModel<SearchScreenModel.State>(State.Init) {
    sealed class State {
        object Init : State()
        object Loading : State()
        data class Result(val state: SearchState) : State()
        data class Error(val message: String) : State()
    }

    fun searchProducts(query: String) {
        mutableState.value = State.Loading
        screenModelScope.launch {
            searchRepository.searchProducts(query).collectLatest { resource ->
                mutableState.value = when (resource) {
                    is Resource.Loading -> State.Loading
                    is Resource.Success -> State.Result(
                        SearchState(
                            query = query,
                            items = emptyList()
                        )
                    )

                    is Resource.Error -> State.Error(message = resource.message)

                }
            }
        }
    }
}
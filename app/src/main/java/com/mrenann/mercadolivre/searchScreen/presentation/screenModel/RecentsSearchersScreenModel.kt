package com.mrenann.mercadolivre.searchScreen.presentation.screenModel

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.mrenann.mercadolivre.searchScreen.domain.repository.QueriesLocalRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RecentsSearchersScreenModel(
    private val repository: QueriesLocalRepository
) : StateScreenModel<RecentsSearchersScreenModel.State>(State.Loading) {
    sealed class State {
        object Loading : State()
        data class Result(val items: List<String>) : State()
        data class Error(val message: String) : State()
    }

    init {
        getQueries()
    }

    fun getQueries() {
        mutableState.value = State.Loading
        screenModelScope.launch {
            try {
                repository.getSearches().collectLatest { list ->
                    mutableState.value = State.Result(
                        items = list
                    )
                }
            } catch (e: Exception) {
                mutableState.value = State.Error(message = e.message ?: "Erro desconhecido")
            }
        }
    }


    fun insertSearchQuery(query: String) {
        screenModelScope.launch {
            repository.insertSearchQuery(query)
        }
    }

}
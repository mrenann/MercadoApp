package com.mrenann.mercadolivre.homeScreen.presentation.screenModel

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeScreenModel : StateScreenModel<HomeScreenModel.State>(State.Loading) {
    sealed class State {
        object Loading : State()
        object Result : State()
    }

    init {
        loadData()
    }

    private fun loadData() {
        mutableState.value = State.Loading

        screenModelScope.launch {
            delay(timeMillis = 2000L)
            mutableState.value = State.Result
        }
    }
}
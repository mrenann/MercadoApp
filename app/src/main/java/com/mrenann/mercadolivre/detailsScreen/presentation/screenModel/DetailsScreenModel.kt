package com.mrenann.mercadolivre.detailsScreen.presentation.screenModel

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.mrenann.mercadolivre.core.domain.model.DetailedProduct
import com.mrenann.mercadolivre.detailsScreen.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsScreenModel(
    private val getProductUseCase: GetProductUseCase
) : StateScreenModel<DetailsScreenModel.State>(State.Loading) {
    sealed class State {
        object Loading : State()
        data class Result(val product: DetailedProduct) : State()
        data class Error(val message: String) : State()
    }

    fun getProduct(id: String) {
        mutableState.value = State.Loading

        screenModelScope.launch {
            getProductUseCase.invoke(id).collectLatest { product ->
                mutableState.value = State.Result(product)
            }
        }
    }


}

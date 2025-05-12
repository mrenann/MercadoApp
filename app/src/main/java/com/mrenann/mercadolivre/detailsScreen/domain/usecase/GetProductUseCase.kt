package com.mrenann.mercadolivre.detailsScreen.domain.usecase

import com.mrenann.mercadolivre.core.domain.model.DetailedProduct
import kotlinx.coroutines.flow.Flow

interface GetProductUseCase {
    suspend operator fun invoke(id: String): Flow<DetailedProduct>
}